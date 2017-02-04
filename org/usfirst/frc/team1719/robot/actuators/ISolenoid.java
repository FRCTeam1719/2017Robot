package org.usfirst.frc.team1719.robot.actuators;

/**
 * Solenoid Interface
 * @author aaron
 */
public interface ISolenoid {
	
	enum states{
		EXTENDED,
		RETRACTED;
	}
	
	/**
	 * Set's the current state of the solenoid. On disable, all solenoids are set to false hardware wise
	 * @param state
	 */
    public void set(boolean state);
    
    void set(states state);
    
    /**
     * get's the current state of the solenoid
     * @return state
     */
    public boolean get();
}
