package org.usfirst.frc.team1719.robot.subsystems;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * 
 * @author jess
 * A simple class for managing the experimental shooter
 * Manages a single SpeedController
 */

public class LogicalExShooter implements IExShooter {

	private SpeedController shooterMotor;
	
	public LogicalExShooter (SpeedController shooterMotor) {
		this.shooterMotor = shooterMotor;
	}
	
	
	@Override
	public void disable() {
		shooterMotor.set(0);
	}

	@Override
	public void setSpeed(double speed) {
		shooterMotor.set(speed);
	}

	@Override
	public double getSpeed() {
		return shooterMotor.get();
	}

}
