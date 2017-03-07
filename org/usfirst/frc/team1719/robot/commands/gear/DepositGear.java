package org.usfirst.frc.team1719.robot.commands.gear;

import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.ResetPositioning;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class DepositGear extends CommandGroup{
	
	
	public DepositGear(IGearHandler gearHandler, IPositionTracker tracker, IDrive drive, IRobot robot){
		addSequential(new ThrowGear(gearHandler));
		addSequential(new ResetPositioning(tracker));
		addSequential(new TimedCommand(1));
//		addSequential(new MoveToPosition(0, -8, tracker,drive,robot,true));
		addSequential(new BreakHard(drive));
		addSequential(new CloseHandler(gearHandler));
		
	}
	


}
