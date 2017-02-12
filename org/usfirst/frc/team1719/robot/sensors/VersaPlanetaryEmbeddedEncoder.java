package org.usfirst.frc.team1719.robot.sensors;

import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.Encoder;

public class VersaPlanetaryEmbeddedEncoder extends Encoder implements IEncoder {

	public final double COUNTS_PER_REVOLUTION = 1024;
	
	public VersaPlanetaryEmbeddedEncoder(int channelA, int channelB, boolean reverseDirection) {
		super(channelA, channelB, reverseDirection, EncodingType.k4X);
	}

	@Override
	public void config(double distancePerRev) {
		setDistancePerPulse(distancePerRev / COUNTS_PER_REVOLUTION);
	}

}
