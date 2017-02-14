package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IIntake;

import edu.wpi.first.wpilibj.command.Command;
/**
 * Unclogs the Intake for the balls while the button is held
 * @author bennyrubin
 *
 */
public class UnclogIntake extends Command{

	private IIntake intake;
	private final double UNCLOG_SPEED = -1;
	
	public UnclogIntake(IIntake intake){
		this.intake = intake;
	}
	@Override
	public void execute(){
		intake.set(UNCLOG_SPEED);
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
