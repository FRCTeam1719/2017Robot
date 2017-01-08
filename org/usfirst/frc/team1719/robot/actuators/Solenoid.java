package org.usfirst.frc.team1719.robot.actuators;

public class Solenoid extends edu.wpi.first.wpilibj.Solenoid implements ISolenoid {
    
    public Solenoid(int channel) {
        super(channel);
    }
    
    public Solenoid(int moduleNumber, int channel) {
        super(moduleNumber, channel);
    }
    
}
