package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.usfirst.frc.team1719.robot.mockHardware.MockAccelerometer;
import org.usfirst.frc.team1719.robot.mockHardware.MockDashboard;
import org.usfirst.frc.team1719.robot.mockHardware.MockEncoder;
import org.usfirst.frc.team1719.robot.mockHardware.MockGyro3D;
import org.usfirst.frc.team1719.robot.mockHardware.MockOI;
import org.usfirst.frc.team1719.robot.mockHardware.MockRobot;
import org.usfirst.frc.team1719.robot.mockHardware.MockSolenoid;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.subsystems.DriveLogic;

public class UseDriveTest {

	MockRobot robot;
	MockOI oi;
	MockDashboard dashboard;
	MockSpeedController leftMotor;
	MockSpeedController rightMotor;
	MockSolenoid shifter;
	MockEncoder leftEnc;
	MockEncoder rightEnc;
	MockGyro3D gyro;
	MockAccelerometer accel;
	DriveLogic drive;
	boolean shifty = false;
	
	
	
	private void setup(){
		oi = new MockOI();
		dashboard = new MockDashboard();
		leftMotor = new MockSpeedController();
		rightMotor = new MockSpeedController();
		shifter = new MockSolenoid();
		leftEnc = new MockEncoder();
		rightEnc = new MockEncoder();
		gyro = new MockGyro3D();
		accel = new MockAccelerometer();
		
		
		robot = new MockRobot(dashboard, oi);
		drive = new DriveLogic(leftMotor, rightMotor, shifter, leftEnc, rightEnc, accel, gyro);
		
	}
	
	
	
	
	@Test
	public void testInitialize() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnd() {
		fail("Not yet implemented");
	}

}
