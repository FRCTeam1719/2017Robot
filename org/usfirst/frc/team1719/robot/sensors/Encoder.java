package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.DigitalSource;

public class Encoder extends edu.wpi.first.wpilibj.Encoder implements IEncoder {
    
    public Encoder(int channelA, int channelB) {
        super(channelA, channelB);
    }
    
    public Encoder(DigitalSource sourceA, DigitalSource sourceB) {
        super(sourceA, sourceB);
    }
    
    public Encoder(int channelA, int channelB, boolean reverseDirection) {
        super(channelA, channelB, reverseDirection);
    }
    
    public Encoder(int channelA, int channelB, int indexChannel) {
        super(channelA, channelB, indexChannel);
    }
    
    public Encoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection) {
        super(sourceA, sourceB, reverseDirection);
    }
    
    public Encoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource) {
        super(sourceA, sourceB, indexSource);
    }
    
    public Encoder(int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
        super(channelA, channelB, reverseDirection, encodingType);
    }
    
    public Encoder(int channelA, int channelB, int indexChannel, boolean reverseDirection) {
        super(channelA, channelB, indexChannel, reverseDirection);
    }
    
    public Encoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection, EncodingType encodingType) {
        super(sourceA, sourceB, reverseDirection, encodingType);
    }
    
    public Encoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource, boolean reverseDirection) {
        super(sourceA, sourceB, indexSource, reverseDirection);
    }
}
