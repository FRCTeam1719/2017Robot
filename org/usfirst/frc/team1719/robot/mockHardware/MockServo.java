package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.IServo;

import edu.wpi.first.wpilibj.tables.ITable;

public class MockServo implements IServo{

	double state;
	double angle;
	
	public MockServo(){
		state = 0;
		angle = 0;
	}
	
	@Override
	public double get() {
		return state;
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public String getSmartDashboardType() {
		//TODO not implemented
		return null;
	}

	@Override
	public void initTable(ITable subtable) {
		// TODO not implemented
	}

	@Override
	public void set(double value) {
		state = value;
	}

	@Override
	public void setAngle(double degress) {
		angle = degress;
	}

	@Override
	public void startLiveWindowMode() {
		// TODO not implemented
	}

	@Override
	public void stopLiveWindowMode() {
		// TODO not implemented
	}

	@Override
	public void updateTable() {
		// TODO not implemented
	}

}
