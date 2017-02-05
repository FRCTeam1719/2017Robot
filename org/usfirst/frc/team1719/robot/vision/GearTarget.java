package org.usfirst.frc.team1719.robot.vision;

import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.subsystems.Block;

public class GearTarget implements VisionTarget{

	/*
	 * Ultimately determines whether the gear vision targets are in frame via multiple steps.
	 * 1) checks whether or not there are two or more objects in the frame (if the pixy is far enough away from 
	 * the targets, they will be recognized together as a single object)
	 * 2) if there are two objects, it checks whether or not they are about the same size, since the gear vision
	 * targets are nearly identical
	 * 3) if they are the same size, it checks that they have the correct dimensions by comparing the height to
	 * width ratio of one of the blocks in frame to the height to width ratio of a single piece of tape
	 * 
	 * If step 1 determines that there is only one object in frame, it checks if that object has the correct 
	 * dimensions using the space between the two pieces of tape
	 */
	@Override
	public boolean inFrame(Block[] frame) {
		if(frame.length >= 2){
			double firstBlockSize = frame[0].hgt * frame[0].wid;
			double secondBlockSize = frame[1].hgt * frame[1].wid;
			
			if(sameSize(firstBlockSize, secondBlockSize)){
				double height = frame[0].hgt;
				double width = frame[0].wid;
				double expectedRatio = 5.0/2.0;
				
				if(correctRatio(height, width, expectedRatio)){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			double height = frame[0].hgt;
			double width = frame[0].wid;
			double expectedRatio = 5.0/10.25;
			
			if(correctRatio(height, width, expectedRatio)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	private boolean sameSize(double firstBlockSize, double secondBlockSize){
		
		final double tolerance = 0.5; //random number that should be changed later
		
		if(Math.abs(firstBlockSize - secondBlockSize) <= tolerance){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean correctRatio(double height, double width, double expectedRatio){
		
		double targetRatio = height / width;
		final double tolerance = 0.5; //random number that should be changed later
		
		if(Math.abs(expectedRatio - targetRatio) <= tolerance){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int[] getCenter(Block[] frame) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
