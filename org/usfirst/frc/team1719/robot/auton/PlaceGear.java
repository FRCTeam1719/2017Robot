package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Place a gear on the hook in auton
 * @author aaron
 *
 */
public class PlaceGear extends CommandGroup{
	
	public enum SIDES {
		LEFT,
		RIGHT,
		CENTER
	}
	
	public PlaceGear(SIDES side, boolean shouldPlace, IDrive drive, IPositionTracker tracker, IGearHandler gear, Robot robot){
		Command toExecute;
		switch(side){
		case LEFT: toExecute = new LeftGearLift(robot, gear, tracker, drive, shouldPlace);break;
		case RIGHT: toExecute = new RightGearLift(robot, gear, tracker, drive, shouldPlace);break;
		case CENTER: toExecute = new CenterGear(robot, drive, gear, tracker, shouldPlace);break;
		default: toExecute = null;
		}
		if(toExecute!=null){
			addSequential(toExecute);
		}
	}
	

}
