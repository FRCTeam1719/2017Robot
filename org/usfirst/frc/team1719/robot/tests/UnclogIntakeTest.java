package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.commands.UnclogIntake;
import org.usfirst.frc.team1719.robot.interfaces.IIntake;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.subsystems.IntakeLogic;

public class UnclogIntakeTest {

	private MockSpeedController controller;
	private UnclogIntake unclog;
	private IIntake intake;
	
	@Before
	public void setUp() throws Exception {
		controller = new MockSpeedController();
		intake = new IntakeLogic(controller);
		unclog = new UnclogIntake(intake);
	}

	@Test
	public void testExecute() {
		unclog.execute();
		assertTrue(controller.get()==-1);
	}
	
	@Test
	public void testEnd(){
		unclog.end();
		assertTrue(controller.get()==0);
	}
	
	
	@Test
	public void testInterrupted(){
		unclog.interrupted();
		assertTrue(controller.get()==0);
	}
}
