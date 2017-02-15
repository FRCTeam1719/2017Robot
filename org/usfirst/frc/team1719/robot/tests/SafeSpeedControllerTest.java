package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.actuators.VexPro775Pro;
import org.usfirst.frc.team1719.robot.mockHardware.MockPDP;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;

import edu.wpi.first.wpilibj.SpeedController;

public class SafeSpeedControllerTest {
	
	VexPro775Pro subject;
	MockPDP pdp;
	SpeedController controller;
	final int port = 0;
	final int VOLT_THRESH = 80;
	
	@Before
	public void setup(){
		System.out.println("Setting up!");
		controller = new MockSpeedController();
		pdp = new MockPDP();
	}

	
	//Tests basic functionality
	@Test
	public void testBasic() throws InterruptedException{
		subject = new VexPro775Pro(controller, port, "testMotor", pdp);
		pdp.setCurrent(port, 0);
		for(double i =-1d;i<1;i+=0.1){
			subject.set(i);
			Thread.sleep(10);
			assertTrue(controller.get()==i);
			subject.stopMotor();
			Thread.sleep(10);
			assertTrue(controller.get()==0);
		}
		subject.disable();
	}
	
	//Tests safe functionality
	@Test
	public void testSafety() throws InterruptedException{
		subject = new VexPro775Pro(controller, port, "testMotor", pdp);
		pdp.setCurrent(port, 0);
		subject.set(1);
		Thread.sleep(10);
		assertTrue(controller.get()==1);
		pdp.setCurrent(port, 80.1);
		//Controller should stop itself
		Thread.sleep(10);
		assertTrue(controller.get()==0);
		//Wait 1000 ms for retry attempt
		pdp.setCurrent(port, 0);
		Thread.sleep(1000);
		assertTrue(controller.get()==1);
		//Fail twice more to trip the disable
		pdp.setCurrent(port, 80.1);
		Thread.sleep(4000);
		assertTrue(controller.get()==0);
		pdp.setCurrent(port, 0);
		Thread.sleep(1000);
		assertTrue(controller.get()==0);
		subject.disable();
	}

}
