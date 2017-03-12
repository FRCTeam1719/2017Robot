package org.usfirst.frc.team1719.robot.commands.gear;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class DepositGear extends CommandGroup{
	
	
	public DepositGear(IGearHandler gearHandler){
		addSequential(new ThrowGear(gearHandler));
		addSequential(new TimedCommand(1));
		addSequential(new CloseHandler(gearHandler));
		
	}
	


}
