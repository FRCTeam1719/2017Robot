package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.BreakHard;
import org.usfirst.frc.team1719.robot.commands.ResetPositioning;
import org.usfirst.frc.team1719.robot.commands.TurnToHeading;
import org.usfirst.frc.team1719.robot.commands.gear.DepositGear;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LeftGearLift extends CommandGroup{
	
	public LeftGearLift(Robot robot, IGearHandler gearHandler, IPositionTracker tracker, IDrive drive){
		//addSequential(new MoveToPosition(0D, 105D, tracker, drive, robot, true));
	    requires((Subsystem) drive);
	    addSequential(/*new DriveConstV(0.5, 0.5, robot, drive, new BooleanSupplier() {
            
            @Override
            public boolean getAsBoolean() {
                System.out.println("Y: " + tracker.getY());
                boolean end = (Math.abs(tracker.getY()) > 110.0D);
                System.out.println("isGeater: " + end);
                return end;
            }
        })/*/new Command() {

	        @Override
	        protected void execute() {
	            drive.moveArcade(0.6, 0);
	        }
	        
            @Override
            protected boolean isFinished() {
                System.out.println("Y: " + tracker.getY());
                boolean end = (Math.abs(tracker.getY()) > 87.0D);
                System.out.println("isGeater: " + end);
                return end;
            }
            
            @Override
            protected void end(){
                drive.moveArcade(0, 0);
            }
	        
	    });
	    addSequential(new BreakHard(drive));
		addSequential(new TurnToHeading(40D, tracker, drive, robot));
		addSequential(new BreakHard(drive));
		addSequential(new ResetPositioning(tracker, drive));
		addSequential(new Command() {

		        private int timeout_count = 0;
		        private double start;
		        @Override
		        protected void initialize(){
		            start = tracker.getY();
		        }
	            @Override
	            protected void execute() {
	                drive.moveArcade(0.6, 0);
	            }
	            
	            @Override
	            protected boolean isFinished() {
	                System.out.println("Y: " + tracker.getY());
	                double difference = tracker.getY() - start;
	                boolean end = (Math.abs(difference) > 20.0D);
	                System.out.println("isGeater: " + end);
	                return end || (timeout_count++ > 100);
	            }
	            
	            @Override
	            protected void end(){
	                drive.moveArcade(0, 0);
	            }
	            
	        });

		addSequential(new BreakHard(drive));
		addSequential(new ResetPositioning(tracker, drive));
		addSequential(new Command() {

	        private double start;
	        @Override
	        protected void initialize(){
	            start = tracker.getY();
	        }
            @Override
            protected void execute() {
                drive.moveArcade(-0.3, 0);
            }
            
            @Override
            protected boolean isFinished() {
                System.out.println("Y: " + tracker.getY());
                double difference = tracker.getY() - start;
                boolean end = (Math.abs(difference) > 2.0D);
                System.out.println("isGeater: " + end);
                return end;
            }
            
            @Override
            protected void end(){
                drive.moveArcade(0, 0);
            }
            
        });
		addSequential(new BreakHard(drive));
		addSequential(new DepositGear(gearHandler));
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
				boolean end = (Math.abs(difference) > 15.0D);
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
