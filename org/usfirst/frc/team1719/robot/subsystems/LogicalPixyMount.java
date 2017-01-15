package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;

import edu.wpi.first.wpilibj.Servo;

public class LogicalPixyMount implements IPixyMount {
	
	private Servo pan;
	private Servo tilt;
	
	public LogicalPixyMount (Servo pan, Servo tilt){
		this.pan = pan;
		this.tilt = tilt;
	}

	@Override
	public void disable() {
		//Nothing to disable
	}

	@Override
	public void setX(double angleX) {
		pan.set(angleX);
	}

	@Override
	public void setY(double angleY) {
		tilt.set(angleY);
	}

	@Override
	public double getX() {
		return pan.get();
	}

	@Override
	public double getY() {
		return tilt.get();
	}

}
