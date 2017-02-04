package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.mockHardware.MockEncoder;
import org.usfirst.frc.team1719.robot.mockHardware.MockOI;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.subsystems.ShooterLogic;

import edu.wpi.first.wpilibj.SpeedController;

public class LogicalExShooterTest {

	SpeedController motor;
	ShooterLogic shooter;
	IOI oi;
	
	
	@Before
	public void setUp() throws Exception {
		motor = new MockSpeedController();
		oi = new MockOI();
		shooter = new ShooterLogic(motor, new MockEncoder(), new MockEncoder());
	}

	@Test
	public void testSetSpeed() {
		for(double i = -1; i < 1; i += 0.01){
			shooter.setSpeed(i);
			assertTrue(motor.get()==i);
		}
	}
	
	@Test
	public void testGetSpeed(){
		for(double i = -1; i < 1; i += 0.01){
			motor.set(i);
			assertTrue(shooter.getSpeed()==i);
		}
	}
	
	@Test
	public void testDisable(){
		motor.set(1);
		shooter.disable();
		assertTrue(motor.get()==0);
	}

}
