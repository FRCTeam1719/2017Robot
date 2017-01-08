package org.usfirst.frc.team1719.robot.mockHardware;

import edu.wpi.first.wpilibj.SpeedController;

public class MockSpeedController implements SpeedController {

	double speed = 0;
	boolean disabled = false;
	boolean inverted = false;
	
	@Override
	public void pidWrite(double output) {
		this.speed = output;
	}

	@Override
	public double get() {
		return speed;
	}

	@Override
	public void set(double speed) {
		if (!disabled) {
			this.speed = speed;
		}
	}

	@Override
	public void setInverted(boolean isInverted) {
		this.inverted = isInverted;
	}

	@Override
	public boolean getInverted() {
		return this.inverted;
	}

	@Override
	public void disable() {
		this.speed = 0;
		this.disabled = true;
	}

	@Override
	public void stopMotor() {
		this.speed = 0;
	}

}
