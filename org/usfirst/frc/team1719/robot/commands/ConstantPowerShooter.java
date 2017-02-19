package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Constants;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	SmartDashboard.putNumber("Shooter speed ", shooter.getEncoderRate() + 0.001 * Math.random());

		shooter.setSpeed(0.6);
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
