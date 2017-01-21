package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class UnclogSilo extends TimedCommand{
	
	private final ISilo silo;
	private final IRobot robot;

	//TODO confirm the unit of time used for timeout
	public UnclogSilo(double timeout, ISilo silo, IRobot robot) {
		super(timeout);
		this.silo = silo;
		this.robot = robot;
		
	}
	
	public void execute (){
		silo.setState(Relay.Value.kReverse);
	}
	
	public void done (){
		silo.setState(Relay.Value.kOff);
	}

}
