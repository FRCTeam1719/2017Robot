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
	public int getX() {
		return 0; // !!! need integer comm.readString(4);
	}
	
	

}
