package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.actuators.Solenoid;
import org.usfirst.frc.team1719.robot.sensors.E4TOpticalEncoder;
import org.usfirst.frc.team1719.robot.sensors.NAVX;
import org.usfirst.frc.team1719.robot.sensors.RS7Encoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
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
    public static final E4TOpticalEncoder leftDriveEnc = new E4TOpticalEncoder(0, 1, true, EncodingType.k2X);
    public static final E4TOpticalEncoder rightDriveEnc = new E4TOpticalEncoder(2, 3, false, EncodingType.k2X);
    public static final RS7Encoder shooterEnc1 = new RS7Encoder(4, 5, true, EncodingType.k4X);
    public static final E4TOpticalEncoder shooterEnc2 = new E4TOpticalEncoder(6, 7, false, EncodingType.k4X);
    
    
    /* PWM */
    public static final SpeedController leftDrive = new Spark(0);
    public static final SpeedController rightDrive = new Spark(1);
    public static final SpeedController exMotorController = new Spark(4);
    
    /* I2C */
    public static final NAVX navx = new NAVX(I2C.Port.kOnboard);
    
    /* Pneumatics */
    public static final Solenoid shifter = new Solenoid(0);
}
