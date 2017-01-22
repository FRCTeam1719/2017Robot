package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.mockHardware.MockUART;
import org.usfirst.frc.team1719.robot.subsystems.LogicalPixyCam;

public class TestPixyCam {
	
	LogicalPixyCam subject;
	MockUART port;
	
	@Before
	public void setup(){
		port = new MockUART();
		subject = new LogicalPixyCam(port);
	}
	
	@Test
	public void testReadX(){
		//assertTrue(subject.getX()=="hey!");
	}
	

}
