package org.usfirst.frc.team1719.robot.actuators;

import java.util.HashMap;

/**
 * Simple wrapper around wpilib Solenoid that allows implements ISolenoid for mocking.
 * @author aaron
 *
 */
public class Solenoid extends edu.wpi.first.wpilibj.Solenoid implements ISolenoid {
	
	HashMap<ISolenoid.states, Boolean> stateMap = new HashMap<>();
	
    public Solenoid(int channel, ISolenoid.states defaultState) {
        super(channel);
        ISolenoid.states other = ISolenoid.states.EXTENDED;
        if(other == defaultState){
        	other = ISolenoid.states.RETRACTED;
        }
        stateMap.put(defaultState, false);
        stateMap.put(other, true);
    }
    
    public Solenoid(int channel){
    	super(channel);
    }
    
    public Solenoid(int moduleNumber, int channel) {
        super(moduleNumber, channel);
    }
    
    @Override
    public void set(ISolenoid.states state){
    	this.set(stateMap.get(state));
    }
    
}
