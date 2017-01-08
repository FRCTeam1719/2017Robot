package org.usfirst.frc.team1719.robot.sensors;

public interface IGyro3D {
    public void resetRoll();
    public void resetPitch();
    public void resetYaw();
    public void getRoll();
    public void getPitch();
    public void getYaw();
    public void getRollRate();
    public void getPitchRate();
    public void getYawRate();
}
