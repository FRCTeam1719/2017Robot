package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassLine extends CommandGroup{
	
	public PassLine(IRobot robot, IDrive drive, IPositionTracker positioning){
		addSequential(new MoveToPosition(0, 120, positioning, drive, robot, true));
		addSequential(new BreakHard)
	}

}
