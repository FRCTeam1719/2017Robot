package org.usfirst.frc.team1719.robot.mockHardware;

import java.util.Hashtable;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;

public class MockDashboard implements IDashboard {

	Hashtable<String, Double> numbers = new Hashtable<String, Double>();
	Hashtable<String, Boolean> booleans = new Hashtable<String, Boolean>();
	Hashtable<String, String> strings = new Hashtable<String, String>();
	@Override
	public void putNumber(String key, double num) {
		numbers.put(key, num);
	}

	@Override
	public double getNumber(String key) {
		return numbers.get(key);
	}

	@Override
	public void putString(String key, String val) {
		strings.put(key, val);
	}

	@Override
	public String getString(String key) {
		return strings.get(key);
	}

	@Override
	public void putBoolean(String key, boolean val) {
		booleans.put(key, val);
	}

	@Override
	public boolean getBoolean(String key) {
		return booleans.get(key);
	}
	
}
