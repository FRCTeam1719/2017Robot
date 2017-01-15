package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.IOI;

import edu.wpi.first.wpilibj.Joystick;

public class MockOI implements IOI {

	double leftX;
	double leftY;
	double rightX;
	double rightY;
	
	boolean shifter;
	@Override
	public double getLeftX() {
		return leftX;
	}

	@Override
	public double getLeftY() {
		return leftY;
	}

	@Override
	public double getRightX() {
		return rightX;
	}

	@Override
	public double getRightY() {
		return rightY;
	}

	@Override
	public boolean getShifter() {
		return shifter;
	}
	
	
	public void setShifter(boolean val){
		shifter = val;
	}
	
	public void setleftX(double newLeftX) {
		this.leftX = newLeftX;
	}
	
	public void setleftY(double newleftY) {
		this.leftY = newleftY;
	}
	
	public void setRightX(double newRightX) {
		this.rightX = newRightX;
	}
	
	public void setRightY(double newRightY) {
		this.rightY = newRightY;
	}

	@Override
	public double getDeviceX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDeviceY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Joystick getDriverJoystick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joystick getOperatorJoystick() {
		// TODO Auto-generated method stub
		return null;
	}

}
