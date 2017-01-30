package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToPosAndHead extends CommandGroup {

    public MoveToPosAndHead(double targetX, double targetY, double targetHeading, double straightDist,
            IPositionTracker posTracker, IDrive drive, IRobot robot) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        double waypointX = targetX - straightDist * Math.sin(Math.toRadians(targetHeading));
        double waypointY = targetY - straightDist * Math.cos(Math.toRadians(targetHeading));
        addSequential(new MoveToPosition(waypointX, waypointY, posTracker, drive, robot, true));
        addSequential(new TurnToHeading(targetHeading, posTracker, drive, robot));
        addSequential(new MoveToPosition(targetX, targetY, posTracker, drive, robot, true, false));
    }
}
