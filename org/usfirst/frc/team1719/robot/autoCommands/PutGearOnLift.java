package org.usfirst.frc.team1719.robot.autoCommands;

import org.usfirst.frc.team1719.robot.commands.DriveToGearLift;
import org.usfirst.frc.team1719.robot.commands.SetGearHandlerToPos;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler.gearStates;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PutGearOnLift extends CommandGroup {

    public PutGearOnLift(IRobot robot, IDrive drive, IPixyMount mount, IPixy pixy, IGearHandler handler) {
        addSequential(new SetGearHandlerToPos(handler, gearStates.TRANSPORT));
        addSequential(new DriveToGearLift(drive, robot, mount.getTarget(), pixy));
        addSequential(new SetGearHandlerToPos(handler, gearStates.DELIVER));
    }
}
