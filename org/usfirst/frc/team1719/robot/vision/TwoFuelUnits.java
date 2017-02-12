package org.usfirst.frc.team1719.robot.vision;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.subsystems.Block;
public class TwoFuelUnits implements VisionTarget{

	@Override
	public boolean inFrame(Block[] frame) {
		boolean result;
		try{
			int fSize = frame[0].heigt*frame[0].width;
			int sSize = frame[1].heigt*frame[1].width;
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
