package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class BreakHard extends InstantCommand{
	
	private final IDrive drive;
	
	public BreakHard(IDrive drive){
		this.drive = drive;
	}
	
	public void execute(){
		drive.moveTank(0, 0);
	}

	
}
