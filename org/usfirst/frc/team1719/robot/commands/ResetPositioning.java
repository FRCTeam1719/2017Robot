package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.subsystems.PositionPhysical;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ResetPositioning extends Command{
	
	private final IPositionTracker tracker;
	private final Timer timer = new Timer();
	
	public ResetPositioning(IPositionTracker tracker){
		try{
			requires((PositionPhysical) tracker);
		}catch(ClassCastException e){
			System.out.println("Running JUnit Test on tracker");
		}
		this.tracker = tracker;
	}
	
	public void initialize(){
		tracker.reset();
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
