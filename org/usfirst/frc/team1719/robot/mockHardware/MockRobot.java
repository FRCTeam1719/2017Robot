package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class MockRobot implements IRobot{
	
	IDashboard dashboard;
	IOI oi;
	public MockRobot(IDashboard dashboard, IOI oi) {
		this.dashboard = dashboard;
		this.oi = oi;
	}
	@Override
	public IOI getOI() {
		return oi;
	}
	@Override
	public IDashboard getDashboard() {
		return dashboard;
	}
	@Override
	public PowerDistributionPanel getPDP() {
		//TODO NOT IMPLEMENTED
		return null;
	}
}
