package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PixyScan extends LimitedRateCommand{
	
	private IPixyMount mount;
	private VisionTarget target;
	private IPixy pixy;
	private IOI oi;
	private Timer cancelTimer;
	private boolean found;
	private double curX;
	private final double CANCELABLE = 0.5;
	private final double TIMEOUT = 30;
	private double curY;
	private double stepX;
	private double stepY;
	
	
	public PixyScan(IPixyMount mount, VisionTarget target, IPixy pixy, IOI oi){
		//Set rate
		super(5);
		this.mount = mount;
		this.target = target;
		this.pixy = pixy;
		this.oi = oi;
		found = false;
		stepX = 0.01;
		stepY = 0.01;
		requires((Subsystem) mount);
		cancelTimer = new Timer();
	}
	
	@Override
	public void initialize(){
		found = false;
		//Move to starting pos
		curX = 0.5;
		curY = 0.5;
		mount.setY(curY);
		mount.setX(curX);
		cancelTimer.start();
	}
	
	

	@Override
	protected boolean isFinished() {
		return found || (cancelTimer.get()>CANCELABLE && oi.getCancelScan()) || cancelTimer.get()>TIMEOUT;
	}

	@Override
	public void action() {
		if(pixy.hasBlocks() && target.inFrame(pixy.getBlocks())){
			//We found the target
			found = true;
		}else{
			//Move on
			curX = curX + stepX;
			mount.setX(curX);
			if(curX <= 0 || curX >= 1){
				stepX = stepX * -1;
			}
			
			if(curX == 0.0 || curX == 0.3 || curX == 0.6 || curX == 1) { 
				boolean goAgain = false;
				int curYCounter = 0;
				while(goAgain) {
					curY = curY + stepY;
					mount.setY(curY);
					if(curY <= 0 || curY >=1){
						stepY = stepY*-1;
					}
					if(curY == 0.5) {
						curYCounter += 1;
					}
					//once the pixy goes up to 1, back to 0, then back to normal .5 
					if(curYCounter == 2) { 
						goAgain = false;
					}
				}
				
					
				
			}
		}
		
	}
	
	@Override
	public void end(){
		cancelTimer.stop();
		cancelTimer.reset();
	}
	
	@Override
	public void interrupted(){
		end();
	}

}
