package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHandlerSubsys extends Subsystem implements IGearHandler{
	
	LogicalGearHandler logic;
	public GearHandlerSubsys(ISolenoid elevator,ISolenoid clawTop, ISolenoid clawBottom){
		logic = new LogicalGearHandler(elevator,clawTop,clawBottom);
	}
	
	@Override
	public void setState(IGearHandler.gearStates state){
		logic.setState(state);
	}
	
	@Override
	public IGearHandler.gearStates getState(){
		return logic.getState();
	}
	
	@Override
	public void disable(){
		logic.disable();
	}
	
	@Override
	public void initDefaultCommand(){
		//None
	}
	
}
