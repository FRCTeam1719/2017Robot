package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.Timer;

public class UTimer extends Timer{
	
	private boolean isSet;
	
	public UTimer(){
		isSet = false;
	}
	
	@Override
	public void start(){
		isSet = true;
		this.start();
	}
	
	@Override
	public void stop(){
		isSet = false;
		this.stop();
	}
	
	public boolean isSet(){
		return isSet;
	}
	
	
	
	
	

}
