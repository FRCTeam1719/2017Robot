package org.usfirst.frc.team1719.robot.subsystems;

/**
 * A 'struct' for storing data about a block of image data from the Pixy.
 * @author Duncan
 */
public class Block {
    public final boolean isCC;
    public final int signature;
    public final int x;
    public final int y;
    public final int width;
    public final int heigt;
    
    public Block(boolean _isCC, int signature, int x, int y, int width, int heigt) {
        isCC = _isCC;
        this.signature = signature;
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigt = heigt;
    }
    
    @Override
    public String toString(){
    	return "Block @"+x+","+y;
    }
    
}
