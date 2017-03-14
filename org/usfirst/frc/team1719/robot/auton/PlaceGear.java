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
		String sideStr;
		switch(side){
		case LEFT: toExecute = new LeftGearLift(robot, gear, tracker, drive, shouldPlace);sideStr="Left";break;
		case RIGHT: toExecute = new RightGearLift(robot, gear, tracker, drive, shouldPlace);sideStr="Center";break;
		case CENTER: toExecute = new CenterGear(robot, drive, gear, tracker, shouldPlace);sideStr="Right";break;
		default: toExecute = null;sideStr="None? This is an error! Passed side was null! Not executing!";
		}
		//Log
		System.out.println("Selected Auton was PlaceGear on the " + sideStr);
		if(toExecute!=null){
			addSequential(toExecute);
		}
	}
	

}
