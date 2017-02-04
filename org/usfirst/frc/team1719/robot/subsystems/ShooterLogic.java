package org.usfirst.frc.team1719.robot.subsystems;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.RS7Encoder;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * 
 * @author jess
 * A simple class for managing the experimental shooter
 * Manages a single SpeedController
 */

public class ShooterLogic implements IExShooter {

	PIDSourceType sourceType = PIDSourceType.kRate;
	private SpeedController shooterMotor;
	private IEncoder encoder1, encoder2;
	
	public ShooterLogic (SpeedController shooterMotor, IEncoder encoder1, IEncoder encoder2) {
		this.shooterMotor = shooterMotor;
		this.encoder1 = encoder1;
		this.encoder2 = encoder2;
		
		encoder1.setDistancePerPulse((4.0 / 30.0));
		//encoder2.config(1 / 60);
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
	public IEncoder getEncoder1() {
		return encoder1;
	}
	
	@Override
	public IEncoder getEncoder2() {
		return encoder2;
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.sourceType = pidSource;
	}


	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}


	@Override
	public double pidGet() {
		if (sourceType == PIDSourceType.kRate) {
			return getAvgEncoderRate();
		}
		else {
			return getAvgEncoderDistance();
		}
	}


	@Override
	public double getAvgEncoderRate() {
		return getEncoder1().getRate();
	}


	@Override
	public double getAvgEncoderDistance() {
		return getEncoder1().getDistance();
	}

}
