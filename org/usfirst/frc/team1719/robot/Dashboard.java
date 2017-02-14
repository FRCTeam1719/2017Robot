package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard implements IDashboard {

	@Override
	public void putNumber(String key, double num) {
		SmartDashboard.putNumber(key, num);
	}

	//Exists For Compatibility
	@Deprecated
	@Override
	public double getNumber(String key) {
		return SmartDashboard.getNumber(key);
	}

	@Override
	public void putString(String key, String val) {
		SmartDashboard.putString(key, val);
	}

	//Exists For Compatibility
	@Deprecated
	@Override
	public String getString(String key) {
		return SmartDashboard.getString(key);
	}

	@Override
	public void putBoolean(String key, boolean val) {
		SmartDashboard.putBoolean(key, val);
	}

	//Exists For Compatibility
	@Deprecated
	@Override
	public boolean getBoolean(String key) {
		return SmartDashboard.getBoolean(key);
	}

	@Override
	public double getNumber(String key, double defaultVal) {
		return SmartDashboard.getNumber(key, defaultVal);
	}

	@Override
	public String getString(String key, String defaultVal) {
		return SmartDashboard.getString(key, defaultVal);
	}

	@Override
	public boolean getBoolean(String key, boolean defaultVal) {
		return SmartDashboard.getBoolean(key, defaultVal);
	}

}
