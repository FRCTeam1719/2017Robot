package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.sensors.UART;

public class MockUART implements UART{

	
	
	@Override
	public String readString(int count) {
		return "hey!";
	}

}
