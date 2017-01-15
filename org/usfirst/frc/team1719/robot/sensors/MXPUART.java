package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.SerialPort;

public class MXPUART implements UART{

	private SerialPort port;
	
	public MXPUART(){
		port = new SerialPort(9600, SerialPort.Port.kMXP);
	}

	@Override
	public String readString(int count) {
		return port.readString(count);
		
	}
	
	
	
	
}
