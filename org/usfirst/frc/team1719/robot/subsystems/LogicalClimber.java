package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IClimber;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;

public class LogicalClimber implements IClimber{
	
	private SpeedController motor;
	private IEncoder encoder;
	
	public LogicalClimber(SpeedController motor, IEncoder encoder) {
		this.motor = motor;
		this.encoder = encoder;
	}

	@Override
	public void disable() {
		motor.set(0);
	}

	@Override
	public void setSpeed(double speed) {
		motor.set(speed);		
	}

	@Override
	public double getSpeed() {
		return motor.get();
	}

	@Override
	public double getRate() {
		return encoder.getRate();
	}

}
