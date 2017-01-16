package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.ExampleCommand;
import org.usfirst.frc.team1719.robot.interfaces.GenericSubsystem;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.subsystems.DriveSubsys;
import org.usfirst.frc.team1719.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team1719.robot.subsystems.PhysicalExShooter;
import org.usfirst.frc.team1719.robot.subsystems.PositionSubsys;

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

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	DriveSubsys drive;
	PhysicalExShooter shooter;
	GenericSubsystem[] subsystems = { drive };
	Display display = new Display();
	IPositionTracker tracker;
	int iter = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		drive = new DriveSubsys(RobotMap.leftDrive, RobotMap.rightDrive, RobotMap.shifter, RobotMap.leftDriveEnc,
				RobotMap.rightDriveEnc, RobotMap.navx, RobotMap.navx, this);
		shooter = new PhysicalExShooter(RobotMap.exMotorController);
		oi.init(this);
		tracker = new PositionSubsys(RobotMap.navx, RobotMap.leftDriveEnc, RobotMap.rightDriveEnc);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		for (int i = 0; i < subsystems.length; i++) {
			if (subsystems[i] != null) {
				subsystems[i].disable();
			}
		}

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
		System.out.println("(x,y)=(" + tracker.getX() + "," + tracker.getY() + "); heading=" + tracker.getHeading() + "deg");
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if((iter++) % 0x10 == 0) {
            display.write(Double.toString(DriverStation.getInstance().getBatteryVoltage()));
        }
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
