package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.CounterBase;

/**
 * Driver class for the E4T Optical Encoder
 * Simple wrapper around the WPILib Encoder that implements IEncoder, for the purposes of mockability, and hardware configs
 * Requires the parameter distance per revolution, so that the internal Encoder object can track distance per tick
 * For specs & more, see the <a href="http://104.131.160.86/index.php/E4T_Miniature_Optical_Encoder">wiki</a> for more.
 * @author Duncan Lowther
 *
 */
public class E4TOpticalEncoder extends edu.wpi.first.wpilibj.Encoder implements IEncoder {

	private final double TICKS_PER_REV = 1440.0;
	private final static CounterBase.EncodingType TYPE = CounterBase.EncodingType.k2X;
	
    
    
	/**
	 * This constructor takes care of setting up the encoding type; as it's unique to this brand
	 * of encoder.
	 * @param channelA
	 * @param channelB
	 * @param reverseDirection
	 * @param distancePerRev Distance per revolution, so that the object can track distance travelled.
	 */
    public E4TOpticalEncoder(int channelA, int channelB, boolean reverseDirection, double distancePerRev){
        super(channelA, channelB, reverseDirection, TYPE);
        this.setDistancePerPulse(distancePerRev/TICKS_PER_REV);
    }
    
}