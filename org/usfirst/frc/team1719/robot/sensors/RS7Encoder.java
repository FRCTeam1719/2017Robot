package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.DigitalSource;

public class RS7Encoder extends edu.wpi.first.wpilibj.Encoder implements IEncoder {

	public RS7Encoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection,
			EncodingType encodingType) {
		super(sourceA, sourceB, reverseDirection, encodingType);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection) {
		super(sourceA, sourceB, reverseDirection);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource,
			boolean reverseDirection) {
		super(sourceA, sourceB, indexSource, reverseDirection);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource) {
		super(sourceA, sourceB, indexSource);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(DigitalSource sourceA, DigitalSource sourceB) {
		super(sourceA, sourceB);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
		super(channelA, channelB, reverseDirection, encodingType);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(int channelA, int channelB, boolean reverseDirection) {
		super(channelA, channelB, reverseDirection);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(int channelA, int channelB, int indexChannel, boolean reverseDirection) {
		super(channelA, channelB, indexChannel, reverseDirection);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(int channelA, int channelB, int indexChannel) {
		super(channelA, channelB, indexChannel);
		// TODO Auto-generated constructor stub
	}


	public RS7Encoder(int channelA, int channelB) {
		super(channelA, channelB);
		// TODO Auto-generated constructor stub
	}


	static public final double TICKS_PER_REV = 12;
	
	
	@Override
	public void config(double distancePerRev) {
		this.setDistancePerPulse(distancePerRev / TICKS_PER_REV);
	}


}
