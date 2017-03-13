package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToTrackerReading extends Command{
	
	private final IDrive drive;
	private final IPositionTracker tracker;
	private final Timer timeout = new Timer();
	private final double timeLimit;
	private final double desiredPosition;
	private final double power;
	private double start;
	
	public MoveToTrackerReading(double desiredPosition, double power, IDrive drive, IPositionTracker tracker){
		this.drive = drive;
		this.tracker = tracker;
		this.desiredPosition = desiredPosition;
		this.power = power;
		//Calculate timeout
		timeLimit = IDrive.LOW_GEAR_SPEED * desiredPosition * 2;
	}
	
	@Override
	public void initialize(){
		timeout.start();
		start = tracker.getY();
	}
	
	@Override
	public void execute(){
		drive.moveArcade(power, 0);
	}
	
	@Override
	public boolean isFinished(){
		boolean moveDistance = Math.abs(tracker.getY() - start) > desiredPosition;
		boolean timedOut = timeout.get() > timeLimit;
		return moveDistance || timedOut;
	}
	
	@Override
	public void end(){
		drive.moveArcade(0, 0);
		timeout.stop();
		timeout.reset();
	}

	@Override
	public void interrupted(){
		end();
	}
}
