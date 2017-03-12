package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IClimber;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

public class ClimberLogic implements IClimber{
	
	private SpeedController motor;
	private IEncoder encoder;
	private DigitalInput limit1;
	private DigitalInput limit2;
	

	public ClimberLogic(SpeedController motor, IEncoder encoder, DigitalInput limit1, DigitalInput limit2) {
		this.motor = motor;
		this.encoder = encoder;
		this.limit1 = limit1;
		this.limit2 = limit2;
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
	
	@Override
	public String toString(){
		return "ClimberLogic";
	}

}
