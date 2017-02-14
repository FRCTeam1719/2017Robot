package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IClimber;
import org.usfirst.frc.team1719.robot.interfaces.IMatchTimer;

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
	
	IClimber climber;
	IMatchTimer timer;
	public UseClimber(IClimber climber, IMatchTimer matchTimer){
		this.climber = climber;
		timer = matchTimer;
	}
	
	boolean shouldRun;
	@Override
	public void initialize(){
		
		
	}
	
	@Override
	public void execute(){
		double time = timer.getMatchTime();
		if(time<=40) {
			shouldRun = true;
		}
		if (shouldRun){
			climber.setSpeed(1); 
			//TODO find out how hanging is going to work
		}
		
	}
	
	@Override
	protected boolean isFinished() {
		
		return false;
	}
	
	@Override
	public void end(){
		climber.disable();
	}
	
	@Override
	public void interrupted(){
		climber.disable();
	}
	
	
	
	
	
	
}
