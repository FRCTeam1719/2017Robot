package org.usfirst.frc.team1719.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * command for climbing
 * @author bennyrubin
 *
 */
public class UseClimber extends Command {
	
	public enum states{
		NOTHING, 
		PREPPING, 
		CLIMBINB, 
		ATTOP;
	}
	
	@Override
	public void execute(){
		
	}
	
	@Override
	protected boolean isFinished() {
		
		return false;
	}
	
	@Override
	public void end(){
		
	}
	
	@Override
	public void interrupted(){
		
	}
	
	
	
	
	
	
}
