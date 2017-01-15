package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixyCam;
import org.usfirst.frc.team1719.robot.sensors.UART;

public class LogicalPixyCam implements IPixyCam{
	
	UART comm;
	public LogicalPixyCam(UART comm){
		this.comm = comm;
	}
	@Override
	public void disable() {
		//Nothing to disable
		
	}

	@Override
	public String getX() {
		return comm.readString(4);
	}
	
	

}
