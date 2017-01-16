package org.usfirst.frc.team1719.robot.subsystems;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * 
 * @author jess
 * A simple class for managing the experimental shooter
 * Manages a single SpeedController
 */

public class LogicalExShooter implements IExShooter {

	private SpeedController shooterMotor;
	private IEncoder encoder;
	
	public LogicalExShooter (SpeedController shooterMotor, IEncoder encoder) {
		this.shooterMotor = shooterMotor;
		this.encoder = encoder;
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


	@Override
	public IEncoder getEncoder() {
		return encoder;
	}

}
