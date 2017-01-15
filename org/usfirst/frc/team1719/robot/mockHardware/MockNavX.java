package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.INAVX;

import edu.wpi.first.wpilibj.PIDSourceType;

public class MockNavX implements INAVX {

	@Override
	public void setRange(Range range) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetRoll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPitch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetYaw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getRoll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYaw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRollRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPitchRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYawRate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
