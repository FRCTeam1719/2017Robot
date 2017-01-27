package org.usfirst.frc.team1719.robot.vision;

import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.subsystems.Block;

public class SingleTarget implements VisionTarget{

	@Override
	public boolean inFrame(Block[] frame) {
		return frame.length>0;
	}

	@Override
	public int[] getCenter(Block[] frame) {
		return new int[] {frame[0].x,frame[0].y};
	}

}
