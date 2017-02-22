package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.RobotMap;
import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.DepositGear;
import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.commands.TurnToHeading;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearLift extends CommandGroup{
	
	public LeftGearLift(Robot robot, IPositionTracker tracker, IDrive drive, ISilo silo){
//        addSequential(new MoveToPosAndHead(33, 69, -90, -36, 100, tracker, drive, robot)); // Right side to right hopper (Red)
		addSequential(new MoveToPosition(0D, 57D, tracker, drive, robot, true));
		addSequential(new TurnToHeading(-44D, tracker, drive, robot));
		addSequential(new BreakHard(drive));
		tracker.reset(0, 0);
		RobotMap.navx.resetYaw();
		drive.getEncoderL().reset();
		drive.getEncoderR().reset();
		addSequential(new MoveToPosition(0D, 30D, tracker, drive, robot, true));
		addSequential(new BreakHard(drive));
		addSequential(new DepositGear(robot.gearHandler));

	}

}
