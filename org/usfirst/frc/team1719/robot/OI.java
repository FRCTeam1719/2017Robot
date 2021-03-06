package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.PixyScan;
import org.usfirst.frc.team1719.robot.commands.RevUpShooter;
import org.usfirst.frc.team1719.robot.commands.RunSilo;
import org.usfirst.frc.team1719.robot.commands.SiloReject;
import org.usfirst.frc.team1719.robot.commands.ToggleIntake;
import org.usfirst.frc.team1719.robot.commands.UnclogIntake;
import org.usfirst.frc.team1719.robot.commands.UseClimber;
import org.usfirst.frc.team1719.robot.commands.gear.DepositGear;
import org.usfirst.frc.team1719.robot.interfaces.GenericSubsystem;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.vision.SingleTarget;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements IOI {
    
    Joystick driver = new Joystick(0);
    Joystick operator = new Joystick(1);
    
    JoystickButton revUpButton = new JoystickButton(operator, 3);
    JoystickButton fireButton = new JoystickButton(operator, 1);
    JoystickButton runSiloBackwards = new JoystickButton(operator, 2);
    
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
        return driver.getRawButton(5);
        
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
    

	public void init(Robot robot) {
		try {
			//revUpButton.toggleWhenPressed(new ConstantPowerShooter(robot.shooter, robot.getDashboard())); 
			revUpButton.whenPressed(new RevUpShooter(robot.shooter, robot, SmartDashboard.getNumber("Desired RevUpShooter speed (RPS): ", 0))); 
			
			
			//TODO Decide on button
			Button intakeToggle = new JoystickButton(operator, 4);
			intakeToggle.whileHeld(new ToggleIntake(robot.intake));
			Button unclogIntake = new JoystickButton(operator, 5);
			unclogIntake.whileHeld(new UnclogIntake(robot.intake));
			Button scanButton = new JoystickButton(driver, 2);
			scanButton.whenPressed(new PixyScan(robot.pixyMount, new SingleTarget(), robot.pixy, robot.getOI()));

			
			
			
			runSiloBackwards.whileHeld(new SiloReject(robot.silo));
			Button runClimber = new JoystickButton(operator, 6);
			runClimber.whileHeld(new UseClimber(robot.climber,robot.timer));
			
			fireButton.whileHeld(new RunSilo(robot.silo, robot.getDashboard()));
			Button depositGear = new JoystickButton(operator, 7);
			depositGear.whenPressed(new DepositGear(robot.gearHandler));
		} catch (NullPointerException e) {
			System.out.println("Subsystem null in OI.init()");
			System.out.println(
					"Likely due to init() being called before all Subsystems in Robot.robotInit() are instantiated.");
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

	@Override
	public boolean getCancelScan() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAbortAutomove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getResetPIDConstants() {
		// TODO Auto-generated method stub
		return false;
	}

}
