package org.usfirst.frc.team1719.robot.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
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

	UseDrive command;
	
	MockRobot mockRobot;
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
	
	public void setup() {
		System.out.println("Entered setup");
		
		leftMotor = new MockSpeedController();
		rightMotor = new MockSpeedController();
		shifter = new MockSolenoid();
		leftEnc = new MockEncoder();
		rightEnc = new MockEncoder();
		gyro = new MockGyro3D();
		accel = new MockAccelerometer();
		
		oi = new MockOI();
		dashboard = new MockDashboard();
		mockRobot = new MockRobot(dashboard, oi);
		
		drive = new DriveLogic(leftMotor, rightMotor, shifter, leftEnc, rightEnc, accel, gyro);
		
		command = new UseDrive( (IDrive) drive, (IRobot) mockRobot);
		command.initialize();
		System.out.println("Instantiated command");
		
	}
	
	@Test
	public void testSetup() {
		setup();
		
		assertTrue(leftMotor.get() == 0);
		assertTrue(rightMotor.get() == 0);
		
		assertTrue(shifter.get() == false);
	}
	
	@Test
	public void testExecute() {
		setup();
		
		dashboard.putNumber(UseDrive.LEFT_DRIVE_KP, 1);
		dashboard.putNumber(UseDrive.LEFT_DRIVE_KI, 1);
		dashboard.putNumber(UseDrive.LEFT_DRIVE_KD, 1);
		dashboard.putNumber(UseDrive.RIGHT_DRIVE_KP, 1);
		dashboard.putNumber(UseDrive.RIGHT_DRIVE_KI, 1);
		dashboard.putNumber(UseDrive.RIGHT_DRIVE_KD, 1);
		assertTrue(leftMotor.get()==0);
		assertTrue(rightMotor.get()==0);
		//i values become left joystick input
		//j values become right joystick input
		for(double i = -1.1; i <= 1.1; i += 0.1){
			oi.setleftY(i);
			for (double j = -1.1; j <= 1.1; j+= 0.1) {
				oi.setRightY(j);
				command.execute();
				if (i < 0) {
					assertTrue(leftMotor.get() < 0);
				}
				else if (i > 0) {
					assertTrue(leftMotor.get() > 0);
				}
				else {
					assertTrue(leftMotor.get() == 0);
				}
				if (j < 0) {
					assertTrue(rightMotor.get() < 0);
				}
				else if (j > 0) {
					assertTrue(rightMotor.get() > 0);
				}
				else {
					assertTrue(rightMotor.get() == 0);
				}
			}
		}
		//Test shifting
		assertFalse(shifter.get());
		oi.setShifter(true);
		command.execute();
		assertTrue(shifter.get());
		command.execute();
		oi.setShifter(false);
		command.execute();
		assertFalse(shifter.get());
		
		
	}
	
}