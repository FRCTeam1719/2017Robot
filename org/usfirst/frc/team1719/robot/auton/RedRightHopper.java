package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.MoveToPosAndHead;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedRightHopper extends CommandGroup{
	
	public RedRightHopper(IRobot robot, IPositionTracker tracker, IDrive drive, ISilo silo){
        addSequential(new MoveToPosAndHead(33, 69, -90, -36, 100, tracker, drive, robot)); // Right side to right hopper (Red)

	}

}
