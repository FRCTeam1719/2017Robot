package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.MoveToTrackerReading;
import org.usfirst.frc.team1719.robot.commands.ResetPositioning;
import org.usfirst.frc.team1719.robot.commands.TurnToHeading;
import org.usfirst.frc.team1719.robot.commands.gear.DepositGear;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drives and places a gear on the left gear lift
 * @author aaron
 *
 */
public class LeftGearLift extends CommandGroup{
	
	public LeftGearLift(Robot robot, IGearHandler gearHandler, IPositionTracker tracker, IDrive drive, boolean placeGear){
	    requires((Subsystem) drive);
	    addSequential(new MoveToTrackerReading(168.0D, 0.5D, drive, tracker));
	    addSequential(new BreakHard(drive));
		addSequential(new TurnToHeading(-40D, tracker, drive, robot));
		addSequential(new BreakHard(drive));
		addSequential(new ResetPositioning(tracker, drive));
		addSequential(new MoveToTrackerReading(32.0D, 0.5D, drive, tracker));
		addSequential(new BreakHard(drive));
		addSequential(new ResetPositioning(tracker, drive));
		addSequential(new MoveToTrackerReading(2.0D, -0.3D, drive, tracker));
		addSequential(new BreakHard(drive));
		if(placeGear){
			addSequential(new DepositGear(gearHandler));
		}
		addSequential(new ResetPositioning(tracker, drive));
		addSequential(new MoveToTrackerReading(30.0D,-0.5D,drive,tracker));
		addSequential(new BreakHard(drive));
	}

}
