package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.commands.MonitorPort;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPixyCam;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.mockHardware.MockDashboard;
import org.usfirst.frc.team1719.robot.mockHardware.MockOI;
import org.usfirst.frc.team1719.robot.mockHardware.MockRobot;
import org.usfirst.frc.team1719.robot.mockHardware.MockUART;
import org.usfirst.frc.team1719.robot.sensors.UART;
import org.usfirst.frc.team1719.robot.subsystems.LogicalPixyCam;

public class MonitorPortTest {
	MonitorPort subject;
	IRobot robot;
	IDashboard dash;
	IOI oi;
	IPixyCam pixy;
	UART port;
	
	@Before
	public void setup(){
		port = new MockUART();
		dash = new MockDashboard();
		oi = new MockOI();
		robot = new MockRobot(dash, oi);
		pixy = new LogicalPixyCam(port);
		subject = new MonitorPort(robot, pixy);
	}
	
	@Test
	public void testExecute(){
		subject.execute();
		assertTrue(dash.getString("CurrentReading")=="hey!");
	}
	

}
