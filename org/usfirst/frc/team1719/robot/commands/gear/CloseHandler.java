package org.usfirst.frc.team1719.robot.commands.gear;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CloseHandler extends InstantCommand{
	
	IGearHandler handler;
	
	public CloseHandler(IGearHandler handler){
		this.handler = handler;
	}
	
	@Override
	public void execute(){
		handler.close();
	}


}
