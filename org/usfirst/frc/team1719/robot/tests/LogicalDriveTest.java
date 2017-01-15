package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.usfirst.frc.team1719.robot.mockHardware.MockAccelerometer;
import org.usfirst.frc.team1719.robot.mockHardware.MockEncoder;
import org.usfirst.frc.team1719.robot.mockHardware.MockGyro3D;
import org.usfirst.frc.team1719.robot.mockHardware.MockNavX;
import org.usfirst.frc.team1719.robot.mockHardware.MockSolenoid;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.subsystems.DriveLogic;

public class LogicalDriveTest {
	
	MockSpeedController leftMotor;
	MockSpeedController rightMotor;
	MockSolenoid shifter;
	MockEncoder leftEnc;
	MockEncoder rightEnc;
	MockGyro3D gyro;
	MockAccelerometer accel;
	MockNavX navx;
	
	DriveLogic drive;
	
	public void setup() {
		leftMotor = new MockSpeedController();
		rightMotor = new MockSpeedController();
		shifter = new MockSolenoid();
		leftEnc = new MockEncoder();
		rightEnc = new MockEncoder();
		gyro = new MockGyro3D();
		accel = new MockAccelerometer();
		navx = new MockNavX();
		
		
		drive = new DriveLogic(leftMotor, rightMotor, shifter, leftEnc, rightEnc, accel, gyro, navx);		
	}
	
	@Test
	public void testInit() {
		setup();
		
		assertTrue(leftMotor.get() == 0);
		assertTrue(rightMotor.get() == 0);
	}
	
	@Test
	public void testShift() {
		setup();
		drive.shift(true);
		assertTrue(shifter.get() == true);
		drive.shift(false);
		assertTrue(shifter.get() == false);
		
	}
	
	@Test
	public void testSetMaxSpeed() {
		setup();
		
		for (double ms = -1.1; ms <= 1.1; ms += 0.1) {
			drive.setMaxSpeed(ms);
			for (double s = -1.1; s <= 1.1; s += 0.1) {
				drive.moveTank(s, s);
				assertTrue(rightMotor.get() <= ms);
				assertTrue(leftMotor.get() <= ms);
			}
		}
	}
}
