package org.usfirst.frc.team1719.robot.interfaces;

import org.usfirst.frc.team1719.robot.subsystems.Block;

/**
 * Simple interface for defining an easy to use test if a complicated vision target is in the frame
 * @author aaron
 */
public interface VisionTarget {
	
	final double PIXY_HEIGHT = 4;
	
	final double PIXY_X_INCHES = 4;
	final double PIXY_Y_INCHES = 4;
	
	final double BOILER_HEIGHT_INCHES = 83;
	
	final double DIFF_HEIGHT = BOILER_HEIGHT_INCHES - PIXY_HEIGHT;
	
	/**
	 * @param frame Blocks returned from the Pixy
	 * @return whether or not the target is in the frame
	 */
	public boolean inFrame(Block[] frame);
	
	/**
	 * Return the center of a complicated vision object
	 * @param frame to analyze
	 * @return x,y
	 */
	public int[] getCenter(Block[] frame);
	
	public static double getDistance(double camPitchDegrees) {
		
		return (DIFF_HEIGHT) / (Math.tan((Math.PI / 180) * camPitchDegrees));
	}
	
	public static double getRobotAngleToTarget(double camYawDegrees, double camDistToTarget) {
		
		double targetVectorX = PIXY_X_INCHES + (camDistToTarget * Math.cos(camYawDegrees * (Math.PI / 180)));
		double targetVectorY = PIXY_Y_INCHES + (camDistToTarget * Math.sin(camYawDegrees * (Math.PI / 180)));
	
		//Angle from (the line parallel to the front of the robot going through the center of the robot)
		// to the (line from the robot to the target)
		double horizontalAngleDegrees = Math.atan(targetVectorY / targetVectorX) * (180 / Math.PI);
		double angleToTargetDegrees = 90 - horizontalAngleDegrees;
		
		return angleToTargetDegrees;
	}
	
}
