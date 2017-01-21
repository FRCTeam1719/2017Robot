package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.ISerial;

/**
 * Logical implementation of IPixy. Deals with parsing incoming bytes into
 * a nicer form.
 * @author Duncan
 */
public class PixyLogic implements IPixy {
    private static final short BLOCK_SYNC = (short) 0xAA55;
    private static final short BLOCK_SYNC_C = (short) 0xAA56;
    private static final short BLOCK_SYNC_ERR = 0x55AA;
    private static final int WORDS_PER_BLOCK = 7;
    
    private final ISerial serial;
    
    private Block[] blocks;
    
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
     */
    public void update() {
        /* Read in the data: 16-bit words, little endian */
        byte[] bytes = serial.read(serial.available());
        short[] words = getWords(bytes);
        
        /* Find the start of the last full frame */
        int framesync1 = -1;
        int framesync0 = -1;
        boolean syncErr = false;
        for(int i = (words.length - 1); i >= 1; i--) {
            if((words[i-1] == BLOCK_SYNC) && ((words[i] == BLOCK_SYNC) || (words[i] == BLOCK_SYNC_C))) {
                if(framesync1 == -1) {
                    framesync1 = (i - 1);
                } else {
                    framesync0 = i;
                    break;
                }
            }
            if(words[i] == BLOCK_SYNC_ERR) {
                syncErr = true;
                break;
            }
        }
        
        /* Out of sync: try again with opposite parity */
        if(syncErr) {
            byte[] newbytes = new byte[bytes.length - 1];
            System.arraycopy(bytes, 1, newbytes, 0, newbytes.length);
            words = getWords(newbytes);
            for(int i = (words.length - 1); i >= 1; i--) {
                if((words[i-1] == BLOCK_SYNC) && ((words[i] == BLOCK_SYNC) || (words[i] == BLOCK_SYNC_C))) {
                    if(framesync1 == -1) {
                        framesync1 = (i - 1);
                    } else {
                        framesync0 = i;
                        break;
                    }
                }
            }
        }
        
        /* Only bother with the words from the current frame */
        short[] frameWords = new short[framesync1 - framesync0];
        System.arraycopy(words, framesync0, frameWords, 0, frameWords.length);
        processFrame(frameWords);
    }
    
    
    private synchronized void processFrame(short[] words) {
        blocks = new Block[words.length / WORDS_PER_BLOCK];
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
                    /* TODO -- handle sync error */
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
                    blocks[blockID] = new Block(curIsCC, cur_sig, cur_x, cur_y, cur_wid, cur_hgt);
                } else {
                    System.err.println("Checksum test failed: checksum=" + cur_checksum
                            + ";calculated_sum=" + sum + ". Discarding block" + blockID + ".");
                    blocks[blockID] = null;
                }
            default:
                System.err.println("Why is <positive integer> mod 7 not an integer between 0 and 6 inclusive?");
        }
    }
    
    /**
     * @return the array of image data blocks from the most recently processed frame
     */
    public synchronized Block[] getBlocks() {
        return blocks;
    }
    
    @Override
    public void disable() {/* No actuators */}
    
    private short[] getWords(byte[] bytes) {
        short[] words = new short[bytes.length / 2];
        for(int i = 0; i < words.length; i++) {
            words[i] = (short) (bytes[2 * i] | (((short)bytes[2 * i + 1]) << 8));
        }
        return words;
    }
}
