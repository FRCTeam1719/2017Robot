package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.Joystick;

public interface IOI {
    public double getLeftX();
    public double getLeftY();
    public double getRightX();
    public double getRightY();
    public boolean getShifter();
    public boolean getLiftGear();
    public double getDeviceX();
    public double getDeviceY();
    
    public Joystick getDriverJoystick();
    public Joystick getOperatorJoystick();
}
