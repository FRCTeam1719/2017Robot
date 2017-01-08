package org.usfirst.frc.team1719.robot.mockHardware;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class MockAccelerometer implements Accelerometer {

	double x = 0;
	double y = 0;
	double z = 0;
	
	@Override
	public void setRange(Range range) {
		
	}

	public void setX(double newX) {
		this.x = newX;
	}
	
	public void setY(double newY) {
		this.y = newY;
	}
	
	public void setZ(double newZ) {
		this.z = newZ;
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

}
