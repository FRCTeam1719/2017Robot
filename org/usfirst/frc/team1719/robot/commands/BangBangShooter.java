package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BangBangShooter extends Command {
	
	final boolean RUN_MOTOR = true;
	final boolean STOP_MOTOR = false;
	
	private boolean runState = STOP_MOTOR;

	IExShooter shooter;
	
	double desiredSpeed = 0;
		
	double motorSpeed = 0.6;
	
	public BangBangShooter(IExShooter shooter) {
		SmartDashboard.putNumber("Shooter Motor speed: ", motorSpeed);
		this.shooter = shooter;
	}
	
	
	@Override
	protected void initialize() {
		this.desiredSpeed = SmartDashboard.getNumber("Desired RevUpShooter speed (RPS): ", 0);
		this.runState = RUN_MOTOR;
	}
	
	@Override
	protected void execute() {
		desiredSpeed = SmartDashboard.getNumber("Desired RevUpShooter speed (RPS): ", 0);
		motorSpeed = SmartDashboard.getNumber("Shooter motor speed: ", 0);
		
		double currSpeed = shooter.getEncoderRate();
		
		if (runState == RUN_MOTOR) {
			if (currSpeed > (desiredSpeed * 1.05)) {
				runState = STOP_MOTOR;
				shooter.setSpeed(0);
			}
			else {
				shooter.setSpeed(motorSpeed);
			}
		}
		else {
			if (currSpeed < (desiredSpeed * 0.95)) {
				runState = RUN_MOTOR;
				shooter.setSpeed(motorSpeed);
			}
			else {
				shooter.setSpeed(0);
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
