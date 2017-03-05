package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.commands.ResetPositioning;
import org.usfirst.frc.team1719.robot.commands.TurnToHeading;
import org.usfirst.frc.team1719.robot.commands.gear.DepositGear;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearLift extends CommandGroup{
	
	public LeftGearLift(Robot robot, IGearHandler gearHandler, IPositionTracker tracker, IDrive drive){
		addSequential(new MoveToPosition(0D, 57D, tracker, drive, robot, true));
		addSequential(new TurnToHeading(-44D, tracker, drive, robot));
		addSequential(new BreakHard(drive));
		addSequential(new ResetPositioning(tracker));
		addSequential(new MoveToPosition(0D, 30D, tracker, drive, robot, true));
		addSequential(new BreakHard(drive));
		addSequential(new DepositGear(gearHandler, tracker, drive, robot));
	}

}
