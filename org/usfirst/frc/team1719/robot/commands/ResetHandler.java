package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ResetHandler extends InstantCommand{
	
	private IGearHandler handler;
	public ResetHandler(IGearHandler handler){
		this.handler = handler;
	}
	
	
	@Override
	public void execute(){
		handler.setState(IGearHandler.gearStates.RECIEVE);
	}

}
