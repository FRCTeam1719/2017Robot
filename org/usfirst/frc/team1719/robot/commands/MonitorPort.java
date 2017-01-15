package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IPixyCam;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class MonitorPort extends InstantCommand{

	IRobot robot;
	IDashboard dash;
	IPixyCam cam;
	
	public MonitorPort(IRobot robot, IPixyCam cam){
		this.robot = robot;
		this.cam = cam;
		this.dash = robot.getDashboard();
	}
	
	@Override
	public void execute(){
		dash.putString("CurrentReading", ""+cam.getX());
	}
	

}
