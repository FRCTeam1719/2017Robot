package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * Driver class for the E4T Optical Encoder
 * Simple wrapper around the WPILib Encoder that implements IEncoder, for the purposes of mockability, and hardware configs
 * Requires the parameter distance per revolution, so that the internal Encoder object can track distance per tick
 * For specs & more, see the <a href="http://104.131.160.86/index.php/E4T_Miniature_Optical_Encoder">wiki</a> for more.
 * @author Duncan Lowther
 *
 */
public class E4TOpticalEncoder extends edu.wpi.first.wpilibj.Encoder implements IEncoder {

	public final double TICKS_PER_REV = 1440.0;
	
	/**
	 * Calculates distance per tick and passes that to the internal Encoder object.
	 * Uses TICKS_PER_REV which is supplied by documentation
	 * @param distancePerRev (distance in ft)
	 */
	public void config(double distancePerRev){
		this.setDistancePerPulse(distancePerRev/TICKS_PER_REV);
	}
    
    public E4TOpticalEncoder(int channelA, int channelB) {
        super(channelA, channelB);
    }
    
    public E4TOpticalEncoder(DigitalSource sourceA, DigitalSource sourceB) {
        super(sourceA, sourceB);
    }
    
    public E4TOpticalEncoder(int channelA, int channelB, boolean reverseDirection) {
        super(channelA, channelB, reverseDirection);
    }
    
    public E4TOpticalEncoder(int channelA, int channelB, int indexChannel) {
        super(channelA, channelB, indexChannel);
    }
    
    public E4TOpticalEncoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection) {
        super(sourceA, sourceB, reverseDirection);
    }
    
    public E4TOpticalEncoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource) {
        super(sourceA, sourceB, indexSource);
    }
    
    public E4TOpticalEncoder(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
        super(channelA, channelB, reverseDirection, encodingType);
    }
    
    public E4TOpticalEncoder(int channelA, int channelB, int indexChannel, boolean reverseDirection) {
        super(channelA, channelB, indexChannel, reverseDirection);
    }
    
    public E4TOpticalEncoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection, EncodingType encodingType) {
        super(sourceA, sourceB, reverseDirection, encodingType);
    }
    
    public E4TOpticalEncoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource, boolean reverseDirection) {
        super(sourceA, sourceB, indexSource, reverseDirection);
    }
}
