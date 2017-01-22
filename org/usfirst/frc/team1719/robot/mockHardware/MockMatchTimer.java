package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.IMatchTimer;
/**
 * Mock matchtimer, used for testing
 * @author bennyrubin
 *
 */
public class MockMatchTimer implements IMatchTimer {
	
	double matchTime;
	public MockMatchTimer(){
	}
	public void setMatchTime(double time){
		matchTime = time;
	}
	@Override
	public double getMatchTime() {
		return matchTime;
	}

}
