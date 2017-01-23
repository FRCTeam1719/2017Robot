package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.actuators.Solenoid;
import org.usfirst.frc.team1719.robot.customHardware.SafeSpeedController;
import org.usfirst.frc.team1719.robot.interfaces.IPDP;
import org.usfirst.frc.team1719.robot.sensors.E4TOpticalEncoder;
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
	
	/* PDP */
	public static final IPDP pdp = new PDP();
    
    /* DIO */
    public static final E4TOpticalEncoder leftDriveEnc = new E4TOpticalEncoder(0, 1);
    public static final E4TOpticalEncoder rightDriveEnc = new E4TOpticalEncoder(2, 3);
    
    /* PWM */
    public static final SpeedController leftDrive = new Spark(0);
    public static final SpeedController rightDrive = new Spark(1);
    public static final SpeedController exMotorController 
    	= new SafeSpeedController(new Spark(2), 2, "Shooter Motor", pdp);
    
    /* I2C */
    public static final NAVX navx = new NAVX(I2C.Port.kOnboard);
    
    /* Pneumatics */
    public static final Solenoid shifter = new Solenoid(0);
}
