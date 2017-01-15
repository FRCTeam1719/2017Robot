package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;
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
	
	
	public UseExShooter(IExShooter exshooter, IRobot robot){
		this.exshooter = exshooter;
		oi = robot.getOI();
	}
	
	
	/**
	 * Squares Joystick value while preserving sign of the original value
	 */
	
	@Override
	public void execute(){
		double joystickvalue = Math.abs(oi.getDeviceX()) * oi.getDeviceX(); 
		
		if (Math.abs(joystickvalue) < DEADZONE_TOLERANCE){
			joystickvalue = 0;
		}
		
		exshooter.setSpeed(joystickvalue);
	}

}
