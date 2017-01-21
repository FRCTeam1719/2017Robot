package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.InstantCommand;
/**
 * The position Tracker
 * @author bennyrubin
 *
 */
public class PositionTracker extends InstantCommand{
	
	
	IPositionTracker tracker;
	IDashboard dashboard;
	
	public PositionTracker(IPositionTracker tracker, IRobot robot){
		this.tracker = tracker;
		dashboard = robot.getDashboard();
	}
	
	@Override
	public void execute() {
		tracker.update();
		dashboard.putBoolean("isTrustworthy",tracker.isTrustworhty());
		dashboard.putNumber("x", tracker.getX());
		dashboard.putNumber("y", tracker.getY());
	}
	

    
    protected void end() {
    	tracker.disable();
    }

   
    protected void interrupted() {
    	tracker.disable();
    }
	
	
	
	

}
