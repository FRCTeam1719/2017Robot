package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IClimber;

import edu.wpi.first.wpilibj.DriverStation;
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
	
	public UseClimber(IClimber climber){
		this.climber = climber;
	}
	
	boolean shouldRun;
	@Override
	public void initialize(){
		double time = DriverStation.getInstance().getMatchTime();
		if(time<=40)
			shouldRun = true;
	}
	
	@Override
	public void execute(){
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
