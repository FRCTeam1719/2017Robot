package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.subsystems.PositionPhysical;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ResetPositioning extends Command{
	
	private final IPositionTracker tracker;
	private final IDrive drive;
	private final Timer timer = new Timer();
	
	public ResetPositioning(IPositionTracker tracker, IDrive drive){
		this.tracker = tracker;
		this.drive = drive;
	}
	
	public void initialize(){
		tracker.reset();
		drive.getEncoderL().reset();
		drive.getEncoderR().reset();
		timer.reset();
		timer.start();
	}
	
	public boolean isFinished(){
		return timer.get() > 0.01;
	}
	
	public void end(){
		timer.stop();
	}
	
	public void interrupted(){
		end();
	}
	
}
