package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class UsePixyMount extends InstantCommand{
	
	private final IPixyMount pixyMount;
	private final IOI oi;
	
	public UsePixyMount(IPixyMount pixyMount, IRobot robot){
		this.pixyMount = pixyMount;
		oi = robot.getOI();
	}
	
	public void execute(){
		double joystickX = oi.getServoX();
		double joystickY = oi.getServoY();
		
		pixyMount.setX(joystickX);
		pixyMount.setY(joystickY);
	}

}
