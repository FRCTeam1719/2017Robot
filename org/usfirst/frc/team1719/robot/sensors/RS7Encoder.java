package org.usfirst.frc.team1719.robot.sensors;

import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.Encoder;

public class RS7Encoder extends Encoder implements IEncoder {

	public static final double TICKS_PER_REV = 12;

	public RS7Encoder(int channelA, int channelB, boolean reverseDirection) {
		super(channelA, channelB, reverseDirection, EncodingType.k4X);
	}

	@Override
	public void config(double distancePerRev) {
		this.setDistancePerPulse(distancePerRev / TICKS_PER_REV);
	}

}
