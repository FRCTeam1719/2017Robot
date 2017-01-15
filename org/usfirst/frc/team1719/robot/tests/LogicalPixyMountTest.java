package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.interfaces.IServo;
import org.usfirst.frc.team1719.robot.mockHardware.MockServo;
import org.usfirst.frc.team1719.robot.subsystems.LogicalPixyMount;

public class LogicalPixyMountTest {

	private IServo pan;
	private IServo tilt;
	private LogicalPixyMount subject;
	
	@Before
	public void setup(){
		pan = new MockServo();
		tilt = new MockServo();
		subject = new LogicalPixyMount(pan,tilt);
	}
	
	
	@Test
	public void testSetx(){
		for(int i=0;i<91;i++){
			subject.setX(i);
			assertTrue(pan.getAngle()==i);
		}
	}
	
	@Test
	public void testSetY(){
		for(int i=0;i<91;i++){
			subject.setY(i);
			assertTrue(tilt.getAngle()==i);
		}
	}
	
	
}
