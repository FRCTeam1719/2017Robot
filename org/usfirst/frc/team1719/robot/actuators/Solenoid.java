package org.usfirst.frc.team1719.robot.actuators;

/**
 * Simple wrapper around wpilib Solenoid that allows implements ISolenoid for mocking.
 * @author aaron
 *
 */
public class Solenoid extends edu.wpi.first.wpilibj.Solenoid implements ISolenoid {
    
    public Solenoid(int channel) {
        super(channel);
    }
    
    public Solenoid(int moduleNumber, int channel) {
        super(moduleNumber, channel);
    }
    
}
