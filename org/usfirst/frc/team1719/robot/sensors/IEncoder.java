package org.usfirst.frc.team1719.robot.sensors;

public interface IEncoder {
    public void reset();
    public boolean getStopped();
    public boolean getDirection();
    public double getDistance();
    public double getRate();
    public void setDistancePerPulse(double distancePerPulse);
    public void setReverseDirection(boolean reverseDirection);
}
