package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IOI;

public class MockRobot {
	
	IDashboard dashboard;
	IOI oi;
	public MockRobot(IDashboard dashboard, IOI oi) {
		this.dashboard = dashboard;
		this.oi = oi;
	}
}
