package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.MoveToTrackerReading;
import org.usfirst.frc.team1719.robot.commands.ResetPositioning;
import org.usfirst.frc.team1719.robot.commands.gear.DepositGear;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterGear extends CommandGroup{
	
	public CenterGear(IRobot robot, IDrive drive, IGearHandler gear, IPositionTracker tracker, boolean placeGear){
	    addSequential(new MoveToTrackerReading(165.0D, 0.5D, drive,tracker));
	    addSequential(new BreakHard(drive));
	    if(placeGear){
	    	addSequential(new DepositGear(gear));
	    }
	    addSequential(new ResetPositioning(tracker, drive));
		addSequential(new MoveToTrackerReading(30.0D,-0.5D,drive,tracker));
		addSequential(new BreakHard(drive));

	}

}
