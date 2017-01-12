package org.usfirst.frc.team1719.robot.sensors;


/**
 * Interface around the WPILib Encoder object. Allows for mockability & device specific settings
 * It's recommended to run config() after creating an object implementing IEncoder
 * @author Duncan Lowther
 *
 */
public interface IEncoder {
    public void reset();
    public boolean getStopped();
    public boolean getDirection();
    public double getDistance();
    public double getRate();
    public void setDistancePerPulse(double distancePerPulse);
    public void setReverseDirection(boolean reverseDirection);
    /**
     * Sets up device specific settings
     * @param distancePerRev (ft)
     */
    public void config(double distancePerRev);
}
