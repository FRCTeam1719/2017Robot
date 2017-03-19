package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightTime extends Command{
	
	private final IDrive drive;
	private final Timer timer = new Timer();
	
	public DriveStraightTime(IDrive drive){
		this.drive = drive;
	}
	
	@Override
	protected void initialize(){
		timer.start();
	}
	
	@Override
	protected void execute(){
		drive.moveArcade(0.5, 0);
	}
	
	@Override
	protected boolean isFinished(){
		return timer.get() > 4;
	}
	
	@Override
	protected void end(){
		drive.moveArcade(0, 0);
		timer.stop();
		timer.reset();
	}
	
	@Override
	protected void interrupted(){
		end();
	}
	

}
