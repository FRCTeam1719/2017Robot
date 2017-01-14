package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class UseExShooter extends InstantCommand {

	private final IExShooter exshooter;
	private final IOI oi;
	private final double DEADZONE_TOLERANCE = 0.05;
	
	
	public UseExShooter(IExShooter exshooter, IRobot robot){
		this.exshooter = exshooter;
		oi = robot.getOI();
	}
	
	
	@Override
	public void execute(){
		double joystickvalue = Math.abs(oi.getDeviceX()) * oi.getDeviceX(); 
		
		if (Math.abs(joystickvalue) < DEADZONE_TOLERANCE){
			joystickvalue = 0;
		}
		
		exshooter.setSpeed(joystickvalue);
	}

}
