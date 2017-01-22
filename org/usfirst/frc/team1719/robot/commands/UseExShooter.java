package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Simple command for managing the Experimental Shooter
 * Control Scheme: While held, sets the motor to square of the joystick
 * @author jess
 *
 */
public class UseExShooter extends InstantCommand {

	private final IExShooter exshooter;
	private final IOI oi;
	private final double DEADZONE_TOLERANCE = 0.05;
	
	double desiredOutput = 0;
	
	public UseExShooter(IExShooter exshooter, IRobot robot){
		this.exshooter = exshooter;
		oi = robot.getOI();
		try {
            requires((Subsystem) exshooter);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
        }
	}
	
	
	/**
	 * Squares Joystick value while preserving sign of the original value
	 */
	
	@Override
	public void execute(){
		double joystickvalue = Math.abs(oi.getDeviceY()) * oi.getDeviceY(); 
		
		if (Math.abs(joystickvalue) < DEADZONE_TOLERANCE){
			joystickvalue = 0;
		}
		
		if (oi.getOperatorJoystick().getRawButton(1)) {
			desiredOutput = 0.1;
		}
		else if (oi.getOperatorJoystick().getRawButton(2)) {
			desiredOutput = 0.45;
		}
		else if (oi.getOperatorJoystick().getRawButton(3)) {
			desiredOutput = 0.3;
		}
		else if (oi.getOperatorJoystick().getRawButton(4)) {
			desiredOutput = 0.4;
		}
		else if (oi.getOperatorJoystick().getRawButton(5)) {
			desiredOutput = 0.5;
		}
		else if (oi.getOperatorJoystick().getRawButton(6)) {
			desiredOutput = 0.6;
		}
		else if (oi.getOperatorJoystick().getRawButton(7)) {
			desiredOutput = 0.61;
		}
		else if (oi.getOperatorJoystick().getRawButton(8)) {
			desiredOutput = 0.65;
		}
		else if (oi.getOperatorJoystick().getRawButton(9)) {
			desiredOutput = 0.625;
		}
		
		System.out.println("Out: " + desiredOutput);
		exshooter.setSpeed(desiredOutput);
	}

}
