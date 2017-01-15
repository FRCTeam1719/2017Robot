package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixyCam;
import org.usfirst.frc.team1719.robot.sensors.UART;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalPixyCam extends Subsystem implements IPixyCam{
	
	LogicalPixyCam logic;
	
	public PhysicalPixyCam(UART port){
		logic = new LogicalPixyCam(port);
	}
	

	@Override
	public void disable() {
		logic.disable();
	}

	@Override
	public int getX() {
		return logic.getX();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
