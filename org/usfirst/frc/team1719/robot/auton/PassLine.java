package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.MoveToTrackerReading;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassLine extends CommandGroup{
	
	public PassLine(IRobot robot, IDrive drive, IPositionTracker tracker){
		System.out.println("Selected auton was: PassLine");
	    addSequential(new MoveToTrackerReading(125.0D,0.5,drive,tracker));
		addSequential(new BreakHard(drive));
	}

}
