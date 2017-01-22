package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IIntake;

import edu.wpi.first.wpilibj.command.Command;
/**
 * Toggles the intake on button press
 * @author bennyrubin
 *
 */
public class ToggleIntake extends Command{

	private IIntake intake;
	
	public ToggleIntake(IIntake intake){
		this.intake = intake;
	}
	@Override
	public void execute(){
		intake.set(1);
	}
	
	@Override
	public void end(){
		intake.disable();
	}
	
	
	@Override
	public boolean isFinished() {
		
		return false;
	}
	
	@Override
	public void interrupted(){
		intake.disable();
	}

}
