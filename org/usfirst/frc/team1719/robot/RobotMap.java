package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.actuators.Solenoid;
import org.usfirst.frc.team1719.robot.sensors.Encoder;
import org.usfirst.frc.team1719.robot.sensors.NAVX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
    
    /* DIO */
    public static final Encoder leftDriveEnc = new Encoder(0, 1);
    public static final Encoder rightDriveEnc = new Encoder(2, 3);
    
    /* PWM */
    public static final SpeedController leftDrive = new Spark(0);
    public static final SpeedController rightDrive = new Spark(1);
    
    /* I2C */
    public static final NAVX navx = new NAVX(I2C.Port.kOnboard);
    
    /* Pneumatics */
    public static final Solenoid shifter = new Solenoid(0);
}
