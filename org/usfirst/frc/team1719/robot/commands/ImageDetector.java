package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;

import edu.wpi.first.wpilibj.command.Command;

public class ImageDetector extends Command{

	private IPixy pixy;
	private VisionTarget target;
	private int frame = 0;
	
	public ImageDetector(IPixy pixy, VisionTarget target){
		this.pixy = pixy;
		this.target = target;
	}
	
	
	@Override
	public void execute(){
		if(frame % 5 == 0){
		synchronized(pixy){
			if(pixy.hasBlocks() && target.inFrame(pixy.getBlocks())){
				System.out.println("Got it!");
			}else{
				System.out.println("Nope!");
			}
		}}
		frame++;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
