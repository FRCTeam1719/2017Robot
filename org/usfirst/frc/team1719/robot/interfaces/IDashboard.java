package org.usfirst.frc.team1719.robot.interfaces;

public interface IDashboard {
	public void putNumber(String key, double num);
	public double getNumber(String key);
	
	public void putString(String key, String val);
	public String getString(String key);
	
	public void putBoolean(String key, boolean val);
	public boolean getBoolean(String key);
	
}
