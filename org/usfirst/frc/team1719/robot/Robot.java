package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.MoveToPosAndHead;
import org.usfirst.frc.team1719.robot.commands.UseDrive;
import org.usfirst.frc.team1719.robot.interfaces.GenericSubsystem;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.sensors.MatchTimer;
import org.usfirst.frc.team1719.robot.subsystems.DriveSubsys;
import org.usfirst.frc.team1719.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team1719.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1719.robot.subsystems.PhysicalClimber;
import org.usfirst.frc.team1719.robot.subsystems.PhysicalExShooter;
import org.usfirst.frc.team1719.robot.subsystems.PhysicalPixyMount;
import org.usfirst.frc.team1719.robot.subsystems.PixySubsys;
import org.usfirst.frc.team1719.robot.subsystems.PositionSubsys;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements IRobot {
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	
	public static double WHEEL_DIAMETER = 6;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	DriveSubsys drive;
	PhysicalExShooter shooter;
	IntakeSubsystem intake;
	MatchTimer timer;
	PhysicalClimber physClimber;
	GenericSubsystem[] subsystems = {drive, shooter, intake, physClimber};
	Display display = new Display();
	IPositionTracker tracker;
	int iter = 0;
	PixySubsys pixy;
	PhysicalPixyMount pixyMount;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		Compressor compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		compressor.start();

		//Drive
		drive = new DriveSubsys(RobotMap.leftDrive, RobotMap.rightDrive, RobotMap.shifter, RobotMap.leftDriveEnc,
				RobotMap.rightDriveEnc, RobotMap.navx, RobotMap.navx, this, WHEEL_DIAMETER * 3.14);
		shooter = new PhysicalExShooter(RobotMap.exMotorController, this, RobotMap.shooterEnc1, RobotMap.shooterEnc2);
		//Shooter
		//Intake
		intake = new IntakeSubsystem(RobotMap.intakeMotor);
		timer = new MatchTimer();
		//Climber
		//TODO make an encoder if necesarry
		physClimber = new PhysicalClimber(RobotMap.climberController,null); 
		//Position tracker Init
		tracker = new PositionSubsys(RobotMap.navx, RobotMap.leftDriveEnc, RobotMap.rightDriveEnc);
		RobotMap.navx.reset();
		//Encoder Init
		RobotMap.leftDriveEnc.config(6.0D * Math.PI * 2.0D /* Hack -- i don't know where the 2 came from*/);
		RobotMap.rightDriveEnc.config(6.0D * Math.PI * 2.0D /* Hack -- i don't know where the 2 came from*/); 

		//Pixy
		pixy = new PixySubsys(RobotMap.pixyI2C);
		pixyMount = new PhysicalPixyMount (RobotMap.pan, RobotMap.tilt, pixy);
		//Setup OI
		oi = new OI();
		oi.init(this);
		//Setup dashboard
		smartdashboardInit();
	}
	
	
	public void smartdashboardInit(){

		SmartDashboard.putNumber(UseDrive.LEFT_DRIVE_KP, 0.01);
		SmartDashboard.putNumber(UseDrive.LEFT_DRIVE_KD, 0);
		
		SmartDashboard.putNumber(UseDrive.RIGHT_DRIVE_KP, 0.01);
		SmartDashboard.putNumber(UseDrive.RIGHT_DRIVE_KD, 0);
		tracker = new PositionSubsys(RobotMap.navx, RobotMap.leftDriveEnc, RobotMap.rightDriveEnc);
		RobotMap.navx.reset();
		RobotMap.leftDriveEnc.config(6.0D * Math.PI * 4.0D /* Hack -- i don't know where the 2 came from*/);
		RobotMap.rightDriveEnc.config(6.0D * Math.PI * 4.0D /* Hack -- i don't know where the 2 came from*/);
		SmartDashboard.putNumber("MoveToPos K[0][P]", 0.1D);
		SmartDashboard.putNumber("MoveToPos K[0][I]", 0.0D);
        SmartDashboard.putNumber("MoveToPos K[0][D]", 0.0D);
        SmartDashboard.putNumber("MoveToPos K[1][P]", 0.01D);
        SmartDashboard.putNumber("MoveToPos K[1][I]", 0.0D);
        SmartDashboard.putNumber("MoveToPos K[1][D]", 0.0D);
        SmartDashboard.putNumber("TurnToHeading K[P]", 0.007);
        SmartDashboard.putNumber("TurnToHeading K[I]", 0.0004);
        SmartDashboard.putNumber("TurnToHeading K[D]", 0);
        oi.init(this);

        chooser.addDefault("Place Gear", new MoveToPosAndHead(0, 65, 0, 24, 100, tracker, drive, this));
        // chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
//		for (int i = 0; i < subsystems.length; i++) {
//			if (subsystems[i] != null) {
//				subsystems[i].disable();
//			}
//		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		if((iter++) % 0x10 == 0) {
		    display.write(Double.toString(DriverStation.getInstance().getBatteryVoltage()));
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
		autonomousCommand = chooser.getSelected();

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
		if((iter++) % 0x10 == 0) {
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
	    //System.out.println("navx " + RobotMap.navx.getYaw() + "lenc" + RobotMap.leftDriveEnc.getDistance() + "renc" + RobotMap.rightDriveEnc.getDistance());
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
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
		return null;
	}
}
