package org.usfirst.frc.team1719.robot.customHardware;

import org.usfirst.frc.team1719.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;

public class VexPro775Pro extends Spark{

	public VexPro775Pro(int channel) {
		super(channel);
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInverted(boolean isInverted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean safeToDrive() {
		return RobotMap.pdp.getCurrent(1) <= 80;
	}

}
