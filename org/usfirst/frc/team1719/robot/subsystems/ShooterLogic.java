package org.usfirst.frc.team1719.robot.subsystems;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

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
	private IEncoder encoder1;
	
	public ShooterLogic (SpeedController shooterMotor, IEncoder encoder1) {
		this.shooterMotor = shooterMotor;
		this.encoder1 = encoder1;
		
		encoder1.setDistancePerPulse((1D));
		encoder1.setSamplesToAverage(8);
	}
	
	
	@Override
	public void disable() {
		shooterMotor.set(0);
	}

	@Override
	
	public void setSpeed(double speed) {
		System.out.println("Shooter spd: " + speed);
		shooterMotor.set(speed);
	}

	@Override
	public double getSpeed() {
		return shooterMotor.get();
	}


	@Override
	public IEncoder getEncoder() {
		return encoder1;
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
			return getEncoderRate();
		}
		else {
			return getEncoderDistance();
		}
	}


	@Override
	public double getEncoderRate() {
		return (getEncoder().getRate());
	}


	@Override
	public double getEncoderDistance() {
		return (getEncoder().getDistance());
	}

	@Override
	public String toString() {
		return "Shooter Logic";
	}
}
