package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveTriangleStunt extends CommandGroup {

    public MoveTriangleStunt(IPositionTracker _posTracker, IDrive _drive, IRobot _robot) {
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
        addSequential(new MoveToPosition(60.0D, 30.0D, _posTracker, _drive, _robot, true));
        addSequential(new Delay(100));
        addSequential(new MoveToPosition(0.0D, 60.0D, _posTracker, _drive, _robot, true));
        addSequential(new Delay(100));
        addSequential(new MoveToPosition(0.0D, 0.0D, _posTracker, _drive, _robot, true));
    }
}
