package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;

public class MockSolenoid implements ISolenoid {

	boolean state;
	@Override
	public void set(boolean on) {
		this.state = on;
	}

	@Override
	public boolean get() {
		return state;
	}

}
