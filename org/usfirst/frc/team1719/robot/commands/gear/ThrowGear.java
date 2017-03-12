package org.usfirst.frc.team1719.robot.commands.gear;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ThrowGear extends InstantCommand{
	
	private IGearHandler handler;
	
	public ThrowGear(IGearHandler handler){
		this.handler = handler;
	}
	
	@Override
	public void execute(){
		System.out.println("Hey");
		handler.open();
	}

}
