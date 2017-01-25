package org.usfirst.frc.team1719.robot.interfaces;

import org.usfirst.frc.team1719.robot.subsystems.Block;

/**
 * Simple interface for defining an easy to use test if a complicated vision target is in the frame
 * @author aaron
 */
public interface VisionTarget {

	/**
	 * @param frame Blocks returned from the Pixy
	 * @return whether or not the target is in the frame
	 */
	public boolean inFrame(Block[] frame);
	
	
}
