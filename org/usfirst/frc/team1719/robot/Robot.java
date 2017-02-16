package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.interfaces.GenericSubsystem;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.sensors.MatchTimer;
import org.usfirst.frc.team1719.robot.subsystems.ClimberPhysical;
import org.usfirst.frc.team1719.robot.subsystems.DrivePhysical;
import org.usfirst.frc.team1719.robot.subsystems.GearHandlerSubsys;
import org.usfirst.frc.team1719.robot.subsystems.IntakePhysical;
import org.usfirst.frc.team1719.robot.subsystems.PixyMountPhysical;
import org.usfirst.frc.team1719.robot.subsystems.PixyPhysical;
import org.usfirst.frc.team1719.robot.subsystems.PositionPhysical;
import org.usfirst.frc.team1719.robot.subsystems.ShooterPhysical;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements IRobot {

	public static OI oi;


	// Subsystems
	DrivePhysical drive;
	ShooterPhysical shooter;
	IntakePhysical intake;
	ClimberPhysical climber;
	IPositionTracker tracker;
	PixyPhysical pixy;
	PixyMountPhysical pixyMount;
	GearHandlerSubsys gearHandler;
	// Array to hold all of the subsystems; so that we can disable them easily
	GenericSubsystem[] subsystems = { drive, shooter, intake, climber, pixy, pixyMount, tracker };
	// Other global references
	Compressor compressor;
	Display display = new Display();
	MatchTimer timer;
	Command autonomousCommand;
	int displayIter = 0;
	Dashboard dashboard;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// General Initialization
		// Setup Compressor
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		compressor.start();
		//Drive
		drive = new DrivePhysical(RobotMap.leftDrive, RobotMap.rightDrive, RobotMap.shifter, RobotMap.leftDriveEnc,
				RobotMap.rightDriveEnc, RobotMap.navx, RobotMap.navx, this);
		shooter = new ShooterPhysical(RobotMap.shooterController, this, RobotMap.shooterEnc1);
		//Shooter
		//Intake
		intake = new IntakePhysical(RobotMap.intakeMotor);
		//Climber
		//TODO make an encoder if necesarry
		climber = new ClimberPhysical(RobotMap.climberController,null); 
		//Gear Handler
		gearHandler = new GearHandlerSubsys(RobotMap.elevator, RobotMap.clawTop, RobotMap.clawBottom);
		//Position tracker Init
		tracker = new PositionPhysical(RobotMap.navx, RobotMap.leftDriveEnc, RobotMap.rightDriveEnc);

		RobotMap.navx.reset();
		timer = new MatchTimer();
		dashboard = new Dashboard();

		// Subsystem Init
		// Pixy
		pixy = new PixyPhysical(RobotMap.pixyI2C);
		// Pixy Mount
		pixyMount = new PixyMountPhysical(RobotMap.pan, RobotMap.tilt, pixy);
		dashboard.putNumber("LED", 0);
		// Setup OI
		// NOTE: This function _must_ be called after subsystem are initialized.
		oi = new OI();
		oi.init(this);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		// Loop through all the subsystems and make sure their state is updated
		for (int i = 0; i < subsystems.length; i++) {
			if (subsystems[i] != null) {
				subsystems[i].disable();
			}
		}
		//RobotMap.lidar.start();
	}

	@Override
	public void disabledPeriodic() {
	    RobotMap.led.setBrightness(dashboard.getNumber("LED", 0));
	    System.out.println("LED: " + dashboard.getNumber("LED", 0));
		Scheduler.getInstance().run();

		if ((displayIter++) % 0x10 == 0) {
			display.write(Double.toString(DriverStation.getInstance().getBatteryVoltage()));
			//System.out.println("LIDAR distance: " + RobotMap.lidar.getDistanceCM() + "cm");
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if ((displayIter++) % 0x10 == 0) {
		    RobotMap.led.setBrightness(dashboard.getNumber("LED", 0));
			display.write(Double.toString(DriverStation.getInstance().getBatteryVoltage()));
		}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		pixyMount.setX(0.5);
		pixyMount.setY(0.5);

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	    RobotMap.led.setBrightness(dashboard.getNumber("LED", 0));

		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	    RobotMap.led.setBrightness(dashboard.getNumber("LED", 0));

		LiveWindow.run();
	}

	public IRobot getInstance() {
		return (IRobot) this;
	}

	@Override
	public IOI getOI() {
		return oi;
	}

	@Override
	public IDashboard getDashboard() {
		return dashboard;
	}
}
