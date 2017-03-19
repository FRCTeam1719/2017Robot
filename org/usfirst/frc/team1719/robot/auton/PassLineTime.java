package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassLineTime extends CommandGroup{
	
	public PassLineTime(IDrive drive){
		addSequential(new DriveStraightTime(drive));
		addSequential(new BreakHard(drive));
	}

}
