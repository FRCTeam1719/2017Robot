package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.mockHardware.MockEncoder;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.subsystems.ClimberLogic;

import edu.wpi.first.wpilibj.SpeedController;

public class LogicalClimberTest {
	
	SpeedController controller;
	MockEncoder encoder;
	ClimberLogic climber;
	
	@Before
	public void setUp() throws Exception {
		controller = new MockSpeedController();
		encoder = new MockEncoder();
		climber = new ClimberLogic(controller, encoder);
	}

	@Test
	public void testSetSpeed() {
		for(int i = 0; i>=1; i+=.1){
			climber.setSpeed(i);
			assertTrue(controller.get()==i);
		}
	}
	
	@Test
	public void testDisable(){
		for(int i = 0; i>=1; i+=.1){
			climber.setSpeed(i);
			climber.disable();
			assertTrue(controller.get()==0);
		}
	}
	
	@Test
	public void testGetSpeed(){
		for(int i = 0; i>=1; i+=.1){
			climber.setSpeed(i);
			assertTrue(controller.get()==climber.getSpeed());
		}
	}
	
	@Test
	public void testGetRate(){
		for(int i = 0; i>=1; i+=.1){
			encoder.setRate(i);
			assertTrue(climber.getRate()==i);
		}
	}

}

