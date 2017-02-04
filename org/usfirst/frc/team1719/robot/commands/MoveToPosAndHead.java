package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveToPosAndHead extends CommandGroup {

    private static final double SPD = 35.0D;
    
    private final IPositionTracker positionTracker;
    private final double tX;
    private final double tY;
    private final double tHead;
    private final int fstimeout;
    private int fsloops = 0;

    public MoveToPosAndHead(double targetX, double targetY, double targetHeading, double straightDist,
            int _fstimeout, IPositionTracker posTracker, IDrive drive, IRobot robot) {
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
        positionTracker = posTracker;
        tX = targetX;
        tY = targetY;
        tHead = targetHeading;
        fstimeout = _fstimeout;
        double waypointX = targetX - straightDist * Math.sin(Math.toRadians(targetHeading));
        double waypointY = targetY - straightDist * Math.cos(Math.toRadians(targetHeading));
        addSequential(new MoveToPosition(waypointX, waypointY, posTracker, drive, robot, true));
        addSequential(new TurnToHeading(targetHeading, posTracker, drive, robot));
        addSequential(new DriveConstV(SPD, SPD, robot, drive, this::fsdone));
    }
    
    
    private boolean fsdone() {
        if(fsloops++ > fstimeout) { /* End if taking too long */
            System.out.println("MTPAH Final state (DCV) timed out.");
            return true;
        }
        /* Hack -- am I at or past the line normal to the heading.*/
        double atanXY = Math.toDegrees(Math.atan2(tX - positionTracker.getX(), tY - positionTracker.getY()));
        System.out.println("MTPAH Final state (DCV): " + atanXY);
        return Math.abs(atanXY - tHead) % 360 >= 90; 
    }
}
