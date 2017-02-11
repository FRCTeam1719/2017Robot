package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.RobotMap;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.vision.GearLift;

/**
 *
 */
public class DriveToGearLift extends MoveForwardDist {

	GearLift target;
	IPixy pixy;
    public DriveToGearLift(IDrive drive, IRobot robot, VisionTarget target, IPixy pixy) {
		super(drive, robot);
		this.target = (GearLift) target;
		this.pixy = pixy;
	}


    @Override
    protected boolean isFinished() {
        return target.closeEnough(pixy.getBlocks()) || RobotMap.lidar.getDistanceCM() <= 20;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.moveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
