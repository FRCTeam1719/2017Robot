package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.tables.ITable;

public interface IServo {

	double get();
	double getAngle();
	String getSmartDashboardType();
	void initTable(ITable subtable);
	void set(double value);
	void setAngle(double degress);
	void startLiveWindowMode();
	void stopLiveWindowMode();
	void updateTable();
	
	
	
}
