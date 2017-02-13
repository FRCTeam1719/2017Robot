package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RunSilo extends Command {
	
	private final ISilo silo;
	
	public RunSilo(ISilo silo, IRobot robot){
		this.silo = silo;
		
		try{
			requires((Subsystem) silo);
		}catch(ClassCastException e){
			System.out.println("Couldn't cast! Probably in a test!");
		}
	}
	
	public void execute() {
		silo.setSpeed(1.0D);				
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		silo.disable();
	}

}
