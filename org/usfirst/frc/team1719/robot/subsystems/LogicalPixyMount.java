package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IServo;

public class LogicalPixyMount implements IPixyMount {
	
	private IServo pan;
	private IServo tilt;
	
	public LogicalPixyMount (IServo pan, IServo tilt){
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
	public double getAngleX() {
		return pan.get();
	}

	@Override
	public double getAngleY() {
		return tilt.get();
	}

}
