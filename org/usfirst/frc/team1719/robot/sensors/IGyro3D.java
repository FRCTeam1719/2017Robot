package org.usfirst.frc.team1719.robot.sensors;

public interface IGyro3D {
    public void resetRoll();
    public void resetPitch();
    public void resetYaw();
    public float getRoll();
    public float getPitch();
    public float getYaw();
    public double getRollRate();
    public double getPitchRate();
    public double getYawRate();
    
}
