package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RunSetDistance extends Command {

	IDrive drive;
	IRobot robot;
	
    public RunSetDistance(IDrive drive, IRobot robot) {
        this.drive = drive;
        this.robot = robot;
        
        try {
        	requires((Subsystem) drive);
        }
        catch (ClassCastException e) {
        	System.out.println("Runing Unit test on runSetDistance");
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Left enc: " + drive.getEncoderL().getDistance() + " | Right enc: " + drive.getEncoderR().getDistance());
    	drive.moveTank(.5, .5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drive.getEncoderL().getDistance() > 100 && drive.getEncoderR().getDistance() > 100;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
