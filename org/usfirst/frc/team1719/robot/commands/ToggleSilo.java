package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ToggleSilo extends Command{
	
	private final ISilo silo;
	
	public ToggleSilo(ISilo silo, IRobot robot){
		this.silo = silo;
		
		try{
			requires((Subsystem) silo);
		}catch(ClassCastException e){
			System.out.println("Couldn't cast! Probably in a test!");
		}
	}
	
	public void execute(){
		silo.setState(Relay.Value.kForward);				
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public void end() {
		silo.setState(Relay.Value.kOff);
		
	}

}
