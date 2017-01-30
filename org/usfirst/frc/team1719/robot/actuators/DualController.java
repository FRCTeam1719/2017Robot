package org.usfirst.frc.team1719.robot.actuators;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

public class DualController implements SpeedController{
	
	private Spark primary;
	private Spark secondary;

	public DualController(int primary, int secondary){
		this.primary = new Spark(primary);
		this.secondary = new Spark(secondary);
	}
	
	
	@Override
	public void pidWrite(double output) {
		primary.pidWrite(output);
		secondary.pidWrite(output);
	}

	@Override
	public double get() {
		return primary.get();
	}

	@Override
	public void set(double speed) {
		primary.set(speed);
		secondary.set(speed);
	}

	@Override
	public void setInverted(boolean isInverted) {
		primary.setInverted(isInverted);
		secondary.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return primary.getInverted();
	}

	@Override
	public void disable() {
		primary.disable();
		secondary.disable();
	}

	@Override
	public void stopMotor() {
		primary.stopMotor();
		secondary.stopMotor();
	}

}
