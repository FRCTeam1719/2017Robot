package org.usfirst.frc.team1719.robot.mockHardware;

import java.util.HashMap;

import org.usfirst.frc.team1719.robot.interfaces.IPDP;

/**
 * PDP that can be used for unit tests
 * @author aaron
 *
 */
public class MockPDP implements IPDP{
	
	private HashMap<Integer, Double> currents;
	
	public MockPDP(){
		//Setup a HashMap to stand as a voltage store
		currents = new HashMap<>();
		for(int i=0;i<16;i++){
			currents.put(i, 0d);
		}
	}
	
	
	
	public void setCurrent(int channel, double current){
		currents.put(channel, current);
	}

	@Override
	public double getCurrent(int channel) {
		return currents.get(channel);
	}

}
