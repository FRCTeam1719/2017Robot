package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.SerialPort;

public class MXPUART extends SerialPort implements UART{
	
	public MXPUART(){
		super(9600, Port.kMXP);
	}
}
