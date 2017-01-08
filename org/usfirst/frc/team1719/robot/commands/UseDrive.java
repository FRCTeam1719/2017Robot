package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class UseDrive extends Command {
    
    private final IRobot robot;
    private final IOI oi;
    private final IDrive drive;
    private boolean isTest;
    private boolean shifted = false;
    
    public UseDrive(IDrive _drive, IRobot _robot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        drive = _drive;
        robot = _robot;
        oi = robot.getOI();
        try {
            requires((Subsystem) drive);
            isTest = false;
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
            isTest = true;
        }
        drive.shift(shifted);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double left = oi.getLeftY(), right = oi.getRightY();
        drive.moveTank(left, right);
        if(shifted != oi.getShifter()) {
            drive.shift(shifted = !shifted);
        }
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
