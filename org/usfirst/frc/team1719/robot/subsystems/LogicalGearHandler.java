package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.actuators.ISolenoid;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

public class LogicalGearHandler implements IGearHandler{
	
	private gearStates currentState = gearStates.DELIVER;
	private ISolenoid[] pistons;
	static{
		ISolenoid.states[] recieveState = new ISolenoid.states[] 
				{ISolenoid.states.EXTENDED,ISolenoid.states.RETRACTED,ISolenoid.states.EXTENDED};
		ISolenoid.states[] grabState = new ISolenoid.states[]
				{ISolenoid.states.EXTENDED,ISolenoid.states.EXTENDED,ISolenoid.states.EXTENDED};
		ISolenoid.states[] transportState = new ISolenoid.states[]
				{ISolenoid.states.RETRACTED,ISolenoid.states.EXTENDED,ISolenoid.states.EXTENDED};
		ISolenoid.states[] positionState = new ISolenoid.states[]
				{ISolenoid.states.RETRACTED,ISolenoid.states.RETRACTED,ISolenoid.states.EXTENDED};
		ISolenoid.states[] deliverState = new ISolenoid.states[]
				{ISolenoid.states.RETRACTED,ISolenoid.states.RETRACTED,ISolenoid.states.RETRACTED};
	}
	
	public LogicalGearHandler(ISolenoid elevator, ISolenoid clawTop, ISolenoid clawBottom){
		pistons = new ISolenoid[] {elevator, clawTop, clawBottom};
	}
	
	/**
	 * Sets the current state of the gear system.
	 * @see <a href="104.131.160.86/index.php/Project_Overview#GearHandler_2> the wiki</a> for more.
	 */
	public void setState(gearStates state){
		ISolenoid.states[] pistonState = null;
		switch(state){
		case RECIEVE: pistonState = LogicalGearHandler.recieveState; break;
		case GRAB: pistonStates = new boolean[] {true,true,true}; break;
		case TRANSPORT: pistonStates = new boolean[] {false,true,true}; break;
		case POSITION: pistonStates = new boolean[] {false,false,true}; break;
		case DELIVER: pistonStates = new boolean[] {false,false,false}; break;
		}
		for(int i=0;i<pistons.length;i++){
			pistons[i].set(pistonStates[i]);
		}
		currentState = state;
	}
	
	public gearStates getState(){
		return currentState;
	}
	
	/**
	 * Disable all solenoids & set the state to deliver
	 */
	public void disable(){
		for(int i=0;i<pistons.length;i++){
			pistons[i].set(false);
		}
		currentState = gearStates.DELIVER;
	}
	
	
	

}
