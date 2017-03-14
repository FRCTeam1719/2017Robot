package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.MoveToPosAndHead;
import org.usfirst.frc.team1719.robot.commands.RevUpShooter;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot extends CommandGroup{
	
	public Shoot(IDrive drive, IPositionTracker tracker, IExShooter shooter, Robot robot){
		addSequential(new PassLine(robot,drive,tracker));
		addSequential(new BreakHard(drive));
		//TODO work out these values
		addSequential(new MoveToPosAndHead(x, y, heading, straightDist, timeout, tracker, drive, robot));
		addSequential(new BreakHard(drive));
		addParallel(new RevUpShooter(shooter, robot, SmartDashboard.getNumber("Desired RevUpShooter speed (RPS): ",0)));
	}

}
