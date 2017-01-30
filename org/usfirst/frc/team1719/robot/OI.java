package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.DriveStraightNoPID;
import org.usfirst.frc.team1719.robot.commands.MoveToPosAndHead;
import org.usfirst.frc.team1719.robot.commands.PixyScan;
import org.usfirst.frc.team1719.robot.commands.RevUpShooter;
import org.usfirst.frc.team1719.robot.commands.ToggleIntake;
import org.usfirst.frc.team1719.robot.commands.TurnToHeading;
import org.usfirst.frc.team1719.robot.commands.UnclogIntake;
import org.usfirst.frc.team1719.robot.commands.UseClimber;
import org.usfirst.frc.team1719.robot.commands.UseExShooter;
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
		revUpButton.whenPressed(new RevUpShooter(robot.shooter, robot, 100));
		

		Button controlShooter = new JoystickButton(operator, 9);

		controlShooter.whileHeld(new UseExShooter(robot.shooter, robot));

		(new JoystickButton(driver, 6)).whenPressed(new DriveStraightNoPID(robot.drive, robot, 0.4));
		(new JoystickButton(driver, 3)).whenPressed(new MoveToPosAndHead(60, 60, 0, 12, robot.tracker, robot.drive, robot));//new MoveToPosition(36, 36, robot.tracker, robot.drive, robot, false));
		(new JoystickButton(driver, 5)).whenPressed(new TurnToHeading(0.0D, robot.tracker, robot.drive, robot));//new MoveToPosition(36, 36, robot.tracker, robot.drive, robot, false));
		//TODO Decide on button
        Button intakeToggle = new JoystickButton(operator, 8);
        intakeToggle.toggleWhenPressed(new ToggleIntake(robot.intake));
        Button unclogIntake = new JoystickButton(operator, 10);
        unclogIntake.whileHeld(new UnclogIntake(robot.intake));
        Button scanButton = new JoystickButton(driver, 2);
        scanButton.whenPressed(new PixyScan(robot.pixyMount, new SingleTarget(), robot.pixy, robot.getOI()));

        //TODO Decide what button this should be.
        Button runClimber = new JoystickButton(operator, 4);
        runClimber.whileHeld(new UseClimber(robot.physClimber,robot.timer));
    }

    @Override
    public boolean getAbortAutomove() {
        return driver.getRawButton(2);
    }

    @Override
    public boolean getResetPIDConstants() {
        return driver.getRawButton(4);
    }
		

	@Override
	public boolean getRevUpShooter() {
		return operator.getRawButton(3);
	}
}
