package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.commands.UseClimber;
import org.usfirst.frc.team1719.robot.interfaces.IClimber;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.interfaces.IMatchTimer;
import org.usfirst.frc.team1719.robot.mockHardware.MockMatchTimer;
import org.usfirst.frc.team1719.robot.subsystems.ClimberLogic;

import edu.wpi.first.wpilibj.SpeedController;

public class UseClimberTest {
	
	UseClimber climber;
	IClimber lClimber;
	SpeedController controller;
	IEncoder encoder;
	IMatchTimer timer;
	
	
	@Before
	public void setUp() throws Exception {
		lClimber = new ClimberLogic(controller, null, null);
		timer = new MockMatchTimer();
		climber = new UseClimber(lClimber, timer);
	}

	@Test
	public void testExecute() {
		for(int i = 120; i<=0; i--){
			climber.execute();
			if(i<=40)
				assertTrue(controller.get()==0);
			if(!(i<=40))
				assertTrue(controller.get()==1);
		}
	}

}
