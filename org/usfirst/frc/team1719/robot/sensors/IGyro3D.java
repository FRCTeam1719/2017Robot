package org.usfirst.frc.team1719.robot.sensors;

public interface IGyro3D {
    public void resetRoll();
    public void resetPitch();
    public void resetYaw();
    public double getRoll();
    public double getPitch();
    public double getYaw();
    public double getRollRate();
    public double getPitchRate();
    public double getYawRate();
}
