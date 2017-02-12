package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.PixyScan;
import org.usfirst.frc.team1719.robot.commands.RevUpShooter;
import org.usfirst.frc.team1719.robot.commands.ToggleIntake;
import org.usfirst.frc.team1719.robot.commands.UnclogIntake;
import org.usfirst.frc.team1719.robot.commands.UseClimber;
import org.usfirst.frc.team1719.robot.commands.UseShooter;
import org.usfirst.frc.team1719.robot.interfaces.GenericSubsystem;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.vision.SingleTarget;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements IOI{

    Joystick driver = new Joystick(0);
    Joystick operator = new Joystick(1);
    
    JoystickButton revUpButton = new JoystickButton(operator, 3);
    
    @Override
    public double getLeftX() {
        return driver.getRawAxis(0);
    }

    @Override
    public double getLeftY() {
        return driver.getRawAxis(1);
    }

    @Override
    public double getRightX() {
        return driver.getRawAxis(4);
    }

    @Override
    public double getRightY() {
        return driver.getRawAxis(5);
    }
    
    public boolean getShifter() {
        return driver.getRawButton(1);

    }


	@Override
	public double getDeviceX() {
		return operator.getRawAxis(0);
	}

	
	public double getDeviceY() {
		return operator.getRawAxis(1);
	}
    
	
	@Override
	public double getServoX() {
		return operator.getRawAxis(0);
	}

	@Override
	public double getServoY() {
		return operator.getRawAxis(1);
	}
	
	@Override
	public boolean getCancelScan(){
		return driver.getRawButton(2);
	}

	public boolean getRevUpShooterButton() {
		return operator.getRawButton(4);
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public void init(Robot robot){
		try {
			revUpButton.whenPressed(new RevUpShooter(robot.shooter, robot, 0)); 
			Button controlShooter = new JoystickButton(operator, 9);
			
			controlShooter.whileHeld(new UseShooter(robot.shooter, robot));
			
			//TODO Decide on button
			Button intakeToggle = new JoystickButton(operator, 8);
			intakeToggle.toggleWhenPressed(new ToggleIntake(robot.intake));
			Button unclogIntake = new JoystickButton(operator, 10);
			unclogIntake.whileHeld(new UnclogIntake(robot.intake));
			Button scanButton = new JoystickButton(driver, 2);
			scanButton.whenPressed(new PixyScan(robot.pixyMount, new SingleTarget(), robot.pixy, robot.getOI()));

			//TODO Decide what button this should be.
			Button runClimber = new JoystickButton(operator, 4);
			runClimber.whileHeld(new UseClimber(robot.climber,robot.timer));
		}
		catch (NullPointerException e) {
			System.out.println("Subsystem null in OI.init()");
			System.out.println("Likely due to init() being called before all Subsystems in Robot.robotInit() are instantiated.");
			System.out.println("List of all instantiated Subsystems: ");
			for (GenericSubsystem subsys : robot.subsystems) {
				if (subsys != null) {
					System.err.println(subsys.toString());
				}
			}
			System.out.println("Stacktrace follows");
			e.printStackTrace();
		}
	}

	@Override
	public boolean getRevUpShooter() {
		return operator.getRawButton(3);
	}
		
	

}
