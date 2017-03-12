package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.ResetPositioning;
import org.usfirst.frc.team1719.robot.commands.gear.DepositGear;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterGear extends CommandGroup{
	
	public CenterGear(IRobot robot, IDrive drive, IGearHandler gear, IPositionTracker tracker){
	    addSequential(new Command() {

	        @Override
	        protected void execute() {
	            drive.moveArcade(0.5, 0);
	        }
	        
            @Override
            protected boolean isFinished() {
                System.out.println("Y: " + tracker.getY());
                boolean end = (Math.abs(tracker.getY()) > 165.0D);
                System.out.println("isGeater: " + end);
                return end;
            }
            
            @Override
            protected void end(){
                drive.moveArcade(0, 0);
            }
	        
	    });
	    addSequential(new BreakHard(drive));
	    addSequential(new DepositGear(gear));
	    addSequential(new ResetPositioning(tracker, drive));
		addSequential(new Command() {
			private double start;
			@Override
			protected void initialize(){
				start = tracker.getY();
			}
			@Override
			protected void execute(){
				drive.moveArcade(-0.5, 0);
			}
			@Override
			protected boolean isFinished(){
				double difference = tracker.getY() - start;
				boolean end = (Math.abs(difference) > 30.0D);
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
