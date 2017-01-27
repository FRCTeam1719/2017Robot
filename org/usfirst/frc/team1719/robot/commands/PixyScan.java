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
	private double step;
	private double curX;
	private final double CANCELABLE = 0.5;
	private final double TIMEOUT = 40;
	
	
	public PixyScan(IPixyMount mount, VisionTarget target, IPixy pixy, IOI oi){
		//Set rate
		super(5);
		this.mount = mount;
		this.target = target;
		this.pixy = pixy;
		this.oi = oi;
		found = false;
		step = 0.01;
		requires((Subsystem) mount);
		cancelTimer = new Timer();
	}
	
	@Override
	public void initialize(){
		found = false;
		//Move to starting pos
		curX = 0.5;
		mount.setY(0.5);
		mount.setX(curX);
		step = 0.01;
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
			curX = curX + step;
			mount.setX(curX);
			System.out.println(curX);
			System.out.println(mount.getAngleX());
			if(curX <= 0 || curX >= 1){
				step = step * -1;
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
