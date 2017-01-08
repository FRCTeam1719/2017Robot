package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.sensors.IGyro3D;

public class MockGyro3D implements IGyro3D {
	
	double roll = 0;
	double rollRate = 0;
	double pitch = 0;
	double pitchRate = 0;
	double yaw = 0;
	double yawRate = 0;
	
	@Override
	public void resetRoll() {
		roll = 0;
	}

	@Override
	public void resetPitch() {
		pitch = 0;
	}

	@Override
	public void resetYaw() {
		yaw = 0;
	}

	public void setRoll(double newRoll) {
		this.roll = newRoll;
	}
	
	public void setRollRate(double newRollRate) {
		this.rollRate = newRollRate;
	}
	
	public void setPitch(double newPitch) {
		this.pitch = newPitch;
	}
	
	public void setPitchRate(double newPitchRate) {
		this.pitchRate = newPitchRate;
	}
	
	public void setYaw(double newYaw) {
		this.yaw = newYaw;
	}
	
	public void setYawRate(double newYawRate) {
		this.yawRate = newYawRate;
	}
	@Override
	public float getRoll() {
		return (float) roll;
	}

	@Override
	public float getPitch() {
		return (float) pitch;
	}

	@Override
	public float getYaw() {
		return (float) yaw;
	}

	@Override
	public double getRollRate() {
		return rollRate;
	}

	@Override
	public double getPitchRate() {
		return pitchRate;
	}

	@Override
	public double getYawRate() {
		return yawRate;
	}

	
	
}
