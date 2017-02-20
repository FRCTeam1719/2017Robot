package org.usfirst.frc.team1719.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.subsystems.Block;
import org.usfirst.frc.team1719.robot.vision.GearLift;

/**
 *
 */
public class DriveToGearLift extends MoveForwardDist {

	double kP = 0.003;
	GearLift target;
	IPixy pixy;
	IPixyMount pixyMount;
    public DriveToGearLift(IDrive drive, IRobot robot, VisionTarget target, IPixy pixy, IPixyMount pixyMount) {
		super(drive, robot);
		this.target = (GearLift) target;
		this.pixy = pixy;
		this.pixyMount = pixyMount;
	}

    double lastErr = 0;
    double lastAngleToCam = 0;
    protected void execute() {
    	double xCurrentServoAngle = pixyMount.getAngleX() - 90;
    	double yCurrentServoAngle = pixyMount.getAngleY() - 90;
    	double err;
    	double xAngleFromCam;
    	ArrayList<Block> goodBlocks = new ArrayList<Block>(3);
    	
    	if (pixy.hasBlocks()) {
    		
    		for (Block b : pixy.getBlocks()) {
    			double yPos = b.y;
    			double yAngle = (yPos - 100) / 4;
    			
    			if (yAngle + yCurrentServoAngle <= 20) {
    				goodBlocks.add(b);
    			}
    		}
    		Block[] blockArray = new Block[goodBlocks.size()];
    		blockArray = goodBlocks.toArray(blockArray);
    		double xCamPos = target.getCenter(blockArray)[0];
    		xAngleFromCam = (xCamPos - 150) / 4;
    		

    		err = xCurrentServoAngle + xAngleFromCam;
    	}
    	else {
    		err = lastErr;
    		xAngleFromCam = lastAngleToCam;
    	}
    	
    	double left = (0.3) + (err * kP);
    	double right = (0.3) - (err * kP);

    	drive.moveTank(left, right);
    	lastErr = err;
    	lastAngleToCam = xAngleFromCam;
    	
    }
    
    @Override
    protected boolean isFinished() {
        return target.closeEnough(pixy.getBlocks());
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
