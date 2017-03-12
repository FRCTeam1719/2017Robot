package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;
import org.usfirst.frc.team1719.robot.mockHardware.MockSolenoid;
import org.usfirst.frc.team1719.robot.subsystems.GearHandlerLogic;

public class GearHandlerTest {
	
	ISolenoid door;
	ISolenoid flap;
	GearHandlerLogic subject;
	
	
	@Before
	public void init(){
		door = new MockSolenoid();
		flap = new MockSolenoid();
		subject = new GearHandlerLogic(door, flap);
	}
	
	@Test
	public void testFlap(){
		assertNotNull(subject);
		assertNotNull(subject.getFlapState());
		subject.setFlap(true);
		assertTrue(subject.getFlapState());
		subject.setFlap(false);
		assertFalse(subject.getFlapState());
	}
	
	@Test
	public void testFlapToggle(){
		subject.setFlap(true);
		subject.toggleFlap();
		assertFalse(subject.getFlapState());
		subject.toggleFlap();
		assertTrue(subject.getFlapState());
	}
	

}
