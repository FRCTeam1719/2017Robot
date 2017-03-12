package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassLine extends CommandGroup{
	
	public PassLine(IRobot robot, IDrive drive, IPositionTracker tracker){
	    addSequential(new Command() {

	        @Override
	        protected void execute() {
	            drive.moveArcade(0.5, 0);
	        }
	        
            @Override
            protected boolean isFinished() {
                System.out.println("Y: " + tracker.getY());
                boolean end = (Math.abs(tracker.getY()) > 125.0D);


                System.out.println("isGeater: " + end);
                return end;
            }
            
            @Override
            protected void end(){
                drive.moveArcade(0, 0);
            }
	        
	    });

		addSequential(new BreakHard(drive));
	}

}
