package org.usfirst.frc.team1719.robot.tests;

import org.usfirst.frc.team1719.robot.commands.RunSilo;
import org.usfirst.frc.team1719.robot.mockHardware.MockDashboard;
import org.usfirst.frc.team1719.robot.mockHardware.MockSpeedController;
import org.usfirst.frc.team1719.robot.subsystems.SiloLogic;

public class UseSiloTest {
	
	MockDashboard dashboard;
	MockSpeedController controller;
	SiloLogic silo;
	RunSilo subject;
	
	public void setup(){
		controller = new MockSpeedController();
		dashboard = new MockDashboard();
		silo = new SiloLogic(controller);
		subject = new RunSilo(silo,dashboard);
	}
	
	

}
