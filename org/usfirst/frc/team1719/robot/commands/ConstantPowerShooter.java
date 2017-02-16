package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Dashboard;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

import edu.wpi.first.wpilibj.command.Command;

public class ConstantPowerShooter extends Command{

	private IExShooter shooter;
	private Dashboard dashboard;
	
	public ConstantPowerShooter(IExShooter shooter){
		this.shooter = shooter;
	}
	
	@Override
	protected void initialize(){
		
	}
	
	@Override
	protected void execute(){
		shooter.setSpeed(0.7);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end(){
		shooter.setSpeed(0);
	}

}
