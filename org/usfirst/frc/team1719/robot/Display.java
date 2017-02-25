package org.usfirst.frc.team1719.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

/**
 * 'Driver' for the MXP four-digit seven-segment display.
 * @author Duncan
 */
public class Display {
    
    I2C i2c = new I2C(Port.kMXP, 0x70);
    public static final Map<Character, byte[]> charmap;
    
    public Display() {
        byte[] osc = new byte[] {(byte) 0x21};
        byte[] blink = new byte[] {(byte) 0x81};
        byte[] bright = new byte[] {(byte) 0xEF};
        
        i2c.writeBulk(osc);
        Timer.delay(.01);
        i2c.writeBulk(blink);
        Timer.delay(.01);
        i2c.writeBulk(bright);
    }
    
    /**
     * Writes a string to the four-digit seven-segment display. Dots ('.') are
     * not counted towards the four-character limit, and only alphanumeric characters
     * (and spaces) are printed.
     * @param s
     */
    public void write(String s) {
    	if(isGood(s)) {
        /* Avoid skipping an initial dot */
        if(s.charAt(0) == '.') { 
            s = " ".concat(s);
        }
        
        /* Remove dots from the string and mark the preceding characters. */
        boolean dots[] = new boolean[4];
        int dot_idx;
        while((dot_idx = s.indexOf('.')) > 0) {
            dots[dot_idx - 1] = true;
            s = s.substring(0, dot_idx).concat(s.substring(dot_idx + 1));
        }
        
        s = s.toUpperCase();
        
        /* Translate the string and dot markers into bytes to send to the display */
        byte[] toSend = new byte[] {0x0F, 0x00, 0, 0, 0, 0, 0, 0, 0, 0};
        for(int i = 0; i < 4; i++) try {
            byte[] chr = charmap.get(s.charAt(i));
            if(dots[i]) {
                chr[1] |= 0x40;
            }
            toSend[8 - 2 * i] = chr[0];
            toSend[9 - 2 * i] = chr[1];
        } catch (Exception e) {/* Don't crash the robot: ignore erroneous inputs */}
        
        /* Perform the write */
        i2c.writeBulk(toSend);
    	}
    }
    /**
     * Takes the String and makes sure all the chars are letters, numbers, or periods
     * It also makes sure that it is no longer than 4 characters
     * @param s
     * @author BennyRubin
     */
    private boolean isGood(String s) {
    	boolean retVal = true;
    	if(s.length() > 4)
    		retVal = false;
    	for(int i = 0; i<s.length(); i++) {
    		charmap.containsKey(s.substring(i));
    	}
    	return retVal;
    }
    
    static {
        charmap = new HashMap<Character, byte[]>();
        charmap.put('0', new byte[] {(byte)0b00111111, (byte)0b00000000});
        charmap.put('1', new byte[] {(byte)0b00000110, (byte)0b00000000});
        charmap.put('2', new byte[] {(byte)0b11011011, (byte)0b00000000});
        charmap.put('3', new byte[] {(byte)0b11001111, (byte)0b00000000});
        charmap.put('4', new byte[] {(byte)0b11100110, (byte)0b00000000});
        charmap.put('5', new byte[] {(byte)0b11101101, (byte)0b00000000});
        charmap.put('6', new byte[] {(byte)0b11111101, (byte)0b00000000});
        charmap.put('7', new byte[] {(byte)0b00000111, (byte)0b00000000});
        charmap.put('8', new byte[] {(byte)0b11111111, (byte)0b00000000});
        charmap.put('9', new byte[] {(byte)0b11101111, (byte)0b00000000});
        charmap.put('A', new byte[] {(byte)0b11110111, (byte)0b00000000});
        charmap.put('B', new byte[] {(byte)0b10001111, (byte)0b00010010});
        charmap.put('C', new byte[] {(byte)0b00111001, (byte)0b00000000});
        charmap.put('D', new byte[] {(byte)0b00001111, (byte)0b00010010});
        charmap.put('E', new byte[] {(byte)0b11111001, (byte)0b00000000});
        charmap.put('F', new byte[] {(byte)0b11110001, (byte)0b00000000});
        charmap.put('G', new byte[] {(byte)0b10111101, (byte)0b00000000});
        charmap.put('H', new byte[] {(byte)0b11110110, (byte)0b00000000});
        charmap.put('I', new byte[] {(byte)0b00001001, (byte)0b00010010});
        charmap.put('J', new byte[] {(byte)0b00011110, (byte)0b00000000});
        charmap.put('K', new byte[] {(byte)0b01110000, (byte)0b00001100});
        charmap.put('L', new byte[] {(byte)0b00111000, (byte)0b00000000});
        charmap.put('M', new byte[] {(byte)0b00110110, (byte)0b00000101});
        charmap.put('N', new byte[] {(byte)0b00110110, (byte)0b00001001});
        charmap.put('O', new byte[] {(byte)0b00111111, (byte)0b00000000});
        charmap.put('P', new byte[] {(byte)0b11110011, (byte)0b00000000});
        charmap.put('Q', new byte[] {(byte)0b00111111, (byte)0b00001000});
        charmap.put('R', new byte[] {(byte)0b11110011, (byte)0b00001000});
        charmap.put('S', new byte[] {(byte)0b10001101, (byte)0b00000001});
        charmap.put('T', new byte[] {(byte)0b00000001, (byte)0b00010010});
        charmap.put('U', new byte[] {(byte)0b00111110, (byte)0b00000000});
        charmap.put('V', new byte[] {(byte)0b00110000, (byte)0b00100100});
        charmap.put('W', new byte[] {(byte)0b00110110, (byte)0b00101000});
        charmap.put('X', new byte[] {(byte)0b00000000, (byte)0b00101101});
        charmap.put('Y', new byte[] {(byte)0b00000000, (byte)0b00010101});
        charmap.put('Z', new byte[] {(byte)0b00001001, (byte)0b00100100});
        charmap.put(' ', new byte[] {(byte)0b00000000, (byte)0b00000000});
    }
}
