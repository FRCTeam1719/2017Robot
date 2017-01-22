package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MoveToPosition extends Command {

	double initX = 0;
	double initY = 0;
	
	double headingSetPoint = 0;
	
	IPositionTracker posTracker;
	
	double desiredX = 0;
	double desiredY = 0;
	
	PIDController desiredHeadingController;
	PIDController rotateController;
	
    public MoveToPosition(double desiredX, double desiredY, IPositionTracker posTracker) {
        
    	this.desiredX = desiredX;
    	this.desiredY = desiredY;
    	
    	this.posTracker = posTracker;
    	try {
    		requires( (Subsystem) posTracker);
    	}
    	catch (ClassCastException e) {
    		System.out.println("Running Unit test on MoveToPosition");
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initX = posTracker.getX();
    	initY = posTracker.getY();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
