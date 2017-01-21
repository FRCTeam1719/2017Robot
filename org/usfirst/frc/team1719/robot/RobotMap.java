package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.actuators.Solenoid;
import org.usfirst.frc.team1719.robot.sensors.E4TOpticalEncoder;
import org.usfirst.frc.team1719.robot.sensors.I2C;
import org.usfirst.frc.team1719.robot.sensors.NAVX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
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
    public static final E4TOpticalEncoder leftDriveEnc;
    public static final E4TOpticalEncoder rightDriveEnc;
    
    /* PWM */
    public static final SpeedController leftDrive;
    public static final SpeedController rightDrive;
    public static final SpeedController exMotorController;
    
    /* I2C */
    public static final NAVX navx;
    public static final I2C pixyI2C;
    
    /* Pneumatics */
    public static final Solenoid shifter;
    static {
        leftDriveEnc = new E4TOpticalEncoder(0, 1, true, EncodingType.k2X);
        rightDriveEnc = new E4TOpticalEncoder(2, 3, false, EncodingType.k2X);
        leftDrive = new Spark(0);
        rightDrive = new Spark(1);
        exMotorController = new Spark(4);
        navx = new NAVX(I2C.Port.kOnboard);
        pixyI2C = new I2C(I2C.Port.kOnboard, 0x54);
        shifter = new Solenoid(0);
    }
}
