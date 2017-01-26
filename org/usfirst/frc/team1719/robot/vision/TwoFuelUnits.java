package org.usfirst.frc.team1719.robot.vision;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.subsystems.Block;
public class TwoFuelUnits implements VisionTarget{

	@Override
	public boolean inFrame(Block[] frame) {
		boolean result;
		try{
//			System.out.println(frame.length);
			int fSize = frame[0].hgt*frame[0].wid;
			int sSize = frame[1].hgt*frame[1].wid;
			result = (fSize > 350) && (sSize > 350);
		}catch(ArrayIndexOutOfBoundsException e){
			result = false;
		}
		return result;
	}
	
	@Override
	public int[] getCenter(Block[] frame){
		int x = (int) Math.round((frame[0].x + frame[1].x)/2);
		int y = (int) Math.round((frame[0].y + frame[1].y)/2);
		return new int[] {x,y};
	}

}
