package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IIntake;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleIntake extends Command{

	private IIntake intake;
	
	public ToggleIntake(IIntake intake){
		this.intake = intake;
	}
	@Override
	protected void execute(){
		intake.set(1);
	}
	
	@Override
	protected void end(){
		intake.disable();
	}
	
	
	@Override
	protected boolean isFinished() {
		
		return false;
	}
	
	@Override
	protected void interrupted(){
		intake.disable();
	}

}
