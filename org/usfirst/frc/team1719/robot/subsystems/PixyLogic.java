package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.ISerial;
import org.usfirst.frc.team1719.robot.sensors.I2C;

/**
 * Logical implementation of IPixy. Deals with parsing incoming bytes into
 * a nicer form.
 * @author Duncan
 */
public class PixyLogic implements IPixy {
    private static final int BLOCK_SYNC = 0xAA55;
    private static final int BLOCK_SYNC_C = 0xAA56;
    private static final int BLOCK_SYNC_ERR_0 = 0x5500;
    private static final int BLOCK_SYNC_ERR_1 = 0x55AA;
    private static final int WORDS_PER_BLOCK = 7;
    
    private final ISerial serial;
    
    private Block[] blocks;
    private boolean trustworthy = false;
    
    /**
     * Instantiates the Pixy connection.
     * @param _serial the serial port to use for communication with the Pixy
     */
    public PixyLogic(ISerial _serial) {
        serial = _serial;
    }
    
    /**
     * Called each iteration to update the block array with new data.
     * @see <a href="http://www.cmucam.org/projects/cmucam5/wiki/Porting_Guide">The Official Pixy Porting Guide</a>
     * @see <a href="http://104.131.160.86/index.php/Pixy_Cam#The_Algorithm">The 1719 wiki page on the Pixy</a>
     */
    public void update() {
        /* Read in the data: 16-bit words, little endian */
        byte[] bytes = new byte[64];
        boolean err = ((I2C) serial).readOnly(bytes, 64);
        //System.out.println("I2C Pixy Says: " + new String(bytes) + " (" + bytes.length + "bytes)");
        int[] words = getWords(bytes);
        /* Fix sync errors */
        while(words[0] == BLOCK_SYNC_ERR_0) {
            //System.out.println("Fixing sync error");
            byte[] newbytes = new byte[bytes.length -1];
            System.arraycopy(bytes, 1, newbytes, 0, newbytes.length);
            bytes = newbytes;
            words = getWords(bytes);
        }
        if(words[0] != BLOCK_SYNC) {
            System.out.println("Unrecoverable sync error");
            trustworthy = false;
            return;
        }
        if(words.length < 8) {
            //System.out.println("Not enough bytes");
            trustworthy = false;
            return;
        }
        /* Discard duplicate sync byte */
        int[] pruned = new int[7];
        System.arraycopy(words, 1, pruned, 0, 7);
        /* Process */
        processFrame(pruned);
        trustworthy = true;
    }
    
    /**
     * Process an array of words back into the blocks they represent.
     * @param words the array of words
     */
    private synchronized void processFrame(int[] words) {
        //System.out.println("Processing " + (words.length / 7) + " blocks (" + words.length + "words)");
        Block[] tempBuffer = new Block[words.length/WORDS_PER_BLOCK];
        boolean isGoodFrame = true;
        boolean curIsCC = false;
        int cur_checksum = -1;
        int cur_sig = -1;
        int cur_x = -1;
        int cur_y = -1;
        int cur_wid = -1;
        int cur_hgt = -1;
        /* Iterate through the words. Every seven words is a 'block' of data about one object. */
        for(int i = 0; i < words.length; i++) switch(i % WORDS_PER_BLOCK) {
            case 0: /* Word 0 is the sync code and type. Is it a normal or 'color code' block? */
                if(words[i] == BLOCK_SYNC) {
                    curIsCC = false;
                } else if (words[i] == BLOCK_SYNC_C) {
                    curIsCC = true;
                } else {
                    //System.out.println("Sync error: expected 0xAA55 or 0xAA56, recieved " + Integer.toHexString(words[i]));
                }
            case 1: /* Word 1 is the checksum for the block*/
                cur_checksum = words[i];
                break;
            case 2: /* Word 2 is the 'signature number' of the object in the frame */
                cur_sig = words[i];
                break;
            case 3: /* Word 3 is the x-position (in pixels) of the center of the object in the frame */
                cur_x = words[i];
                break;
            case 4: /* Word 4 is the y-position (in pixels) of the center of the object in the frame */
                cur_y = words[i];
                break;
            case 5: /* Word 5 is the width (in pixels) of the object in the frame */
                cur_wid = words[i];
                break;
            case 6: /* Word 6 is the height (in pixels) of the object in the frame */
                cur_hgt = words[i];
                int sum = cur_sig + cur_x + cur_y + cur_wid + cur_hgt;
                int blockID = i / WORDS_PER_BLOCK;
                if(sum == cur_checksum) {
                    tempBuffer[blockID] = new Block(curIsCC, cur_sig, cur_x, cur_y, cur_wid, cur_hgt);
                    System.out.println("" + cur_wid + "x" + cur_hgt + "block detected, centered at (" + cur_x + ", " + cur_y + ")");
                } else {
                    System.out.println("Checksum test failed: checksum=" + cur_checksum
                            + ";calculated_sum=" + sum + ". Discarding block" + blockID + ".");
                    tempBuffer[blockID] = null;
                    isGoodFrame = false;
                }
                break;
            default:
                System.err.println("Why is <positive integer> mod 7 not an integer between 0 and 6 inclusive?");
        }
        if(isGoodFrame){
        	//Update the blocks with the temp buffer
        	blocks = tempBuffer;
        	trustworthy = true;
        }else{
        	trustworthy = false;
        }
    }
    
    /**
     * @return the array of image data blocks from the most recently processed frame
     */
    public synchronized Block[] getBlocks() {
        return blocks;
    }
    
    public synchronized boolean hasBlocks() {
    	return (blocks!=null)&&( blocks.length > 0);
    }
    /**
     * @return whether the blocks are current. If a transmission error occurs, the blocks may be outdated.
     */
    public boolean isTrustworthy() {
        return trustworthy;
    }
    
    @Override
    public void disable() {/* No actuators */}
    
    /**
     * Translates a little-endian stream of bytes into an array of unsigned 16-bit words.
     * @param bytes the raw byte stream
     * @return the array of words represented by the byte stream
     */
    private int[] getWords(byte[] bytes) {
        int[] words = new int[bytes.length / 2];
        for(int i = 0; i < words.length; i++) {
            words[i] = (bytes[2 * i] & 0xFF) | ((bytes[2 * i + 1] & 0xFF) << 8);
        }
        return words;
    }
}
