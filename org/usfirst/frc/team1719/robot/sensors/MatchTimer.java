package org.usfirst.frc.team1719.robot.sensors;

import org.usfirst.frc.team1719.robot.interfaces.IMatchTimer;

import edu.wpi.first.wpilibj.DriverStation;
/**
 * Match timer used to get the time left in the game 
 * @author bennyrubin
 *
 */
public class MatchTimer implements IMatchTimer{

	@Override
	public double getMatchTime() {
		//TODO change to DriverStation.getInstance().getMatchTime();
		return 0;
	}

}
