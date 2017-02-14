package org.usfirst.frc.team1719.robot.interfaces;

/**
 * Interface around SmartDashboard for mockability
 * @author aaron
 *
 */
public interface IDashboard {
	public void putNumber(String key, double num);
	public double getNumber(String key);
	public double getNumber(String key, double defaultVal);
	
	public void putString(String key, String val);
	public String getString(String key);
	public String getString(String key, String defaultVal);
	
	public void putBoolean(String key, boolean val);
	public boolean getBoolean(String key);
	public boolean getBoolean(String key, boolean defaultVal);
	
}
