package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SimpleDrive extends Command{
	
	private Timer timer = new Timer();
	private IDrive drive;
	
	public SimpleDrive(IDrive drive){
		this.drive = drive;
	}
	
	@Override
	public void initialize(){
		timer.reset();
		timer.start();
	}
	
	@Override
	public void execute(){
		drive.moveTank(.5, .5);
	}
	
	@Override
	public boolean isFinished(){
		return timer.get() > 23;
	}
	
	@Override
	public void end(){
		drive.moveTank(0, 0);
	}
	
	@Override
	public void interrupted(){
		end();
	}

}
