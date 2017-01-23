package org.usfirst.frc.team1719.robot.subsystems;

/**
 * A 'struct' for storing data about a block of image data from the Pixy.
 * @author Duncan
 */
public class Block {
    public final boolean isCC;
    public final int sig;
    public final int x;
    public final int y;
    public final int wid;
    public final int hgt;
    
    public Block(boolean _isCC, int _sig, int _x, int _y, int _wid, int _hgt) {
        isCC = _isCC;
        sig = _sig;
        x = _x;
        y = _y;
        wid = _wid;
        hgt = _hgt;
    }
}
