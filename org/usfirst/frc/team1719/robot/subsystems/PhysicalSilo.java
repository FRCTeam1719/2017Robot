package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalSilo extends Subsystem implements ISilo{
	
	LogicalSilo logic;
	IRobot robot;
	
	public PhysicalSilo (Relay relay, IRobot robot){
		logic = new LogicalSilo(relay);
		this.robot = robot;
	}

	@Override
	public void disable() {
		logic.disable();
		
	}

	@Override
	public void setState(Value state) {
		logic.setState(state);
	}

	@Override
	public Value getState() {
		return logic.getState();
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
