package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PositionTracker extends InstantCommand{
	
	
	IPositionTracker tracker;
	IDashboard dashboard;
	
	public PositionTracker(IPositionTracker tracker, IRobot robot){
		this.tracker = tracker;
		dashboard = robot.getDashboard();
	}
	
	
	
	

}
