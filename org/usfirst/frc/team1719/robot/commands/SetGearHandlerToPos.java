package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetGearHandlerToPos extends InstantCommand {

	IGearHandler gearHandler;
	IGearHandler.gearStates desiredState;
	
	public SetGearHandlerToPos(IGearHandler handler, IGearHandler.gearStates state) {
		this.gearHandler = handler;
		this.desiredState = state;
	}
	@Override
	protected void execute() {
		gearHandler.setState(desiredState);
	}

}
