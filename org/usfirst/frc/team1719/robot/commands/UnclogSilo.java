package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class UnclogSilo extends TimedCommand{
	
	private final ISilo silo;

	public UnclogSilo(double timeout, ISilo silo) {
		super(timeout);
		this.silo = silo;
	}
	
	public void execute (){
		silo.setState(Relay.Value.kReverse);
	}
	
	protected void end (){
		silo.setState(Relay.Value.kOff);
	}

}
