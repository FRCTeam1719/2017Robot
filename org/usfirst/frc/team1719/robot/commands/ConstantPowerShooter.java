package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Constants;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

import edu.wpi.first.wpilibj.command.Command;

public class ConstantPowerShooter extends Command{

	private final IExShooter shooter;
	private final IDashboard dashboard;
	
	public ConstantPowerShooter(IExShooter shooter, IDashboard dashboard){
		this.shooter = shooter;
		this.dashboard = dashboard;
	}
	
	@Override
	protected void initialize(){
		dashboard.putBoolean(Constants.SHOOTER_RUNNING, true);
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
		dashboard.putBoolean(Constants.SHOOTER_RUNNING, false);
	}

}
