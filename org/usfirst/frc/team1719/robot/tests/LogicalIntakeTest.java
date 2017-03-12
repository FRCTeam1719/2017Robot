package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.subsystems.IntakeLogic;

public class LogicalIntakeTest {

	private MockSpeedController controller;
	private IntakeLogic intake;
	@Before
	public void setUp() throws Exception {
		controller = new MockSpeedController();
		intake = new IntakeLogic(controller);
	}

	@Test
	public void testSet() {
		for(double i=-1;i<1;i+=0.1){
			intake.set(i);
			assertTrue(controller.get()==i);
		}
	}
	
	@Test
	public void testDisable(){
		for(double i=-1;i<1;i+=0.1){
			intake.set(i);
			intake.disable();
			assertTrue(controller.get()==0);
		}
	}
	

}
