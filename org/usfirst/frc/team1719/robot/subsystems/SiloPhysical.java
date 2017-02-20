package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SiloPhysical extends Subsystem implements ISilo{
	
	SiloLogic logic;
	IRobot robot;
	
	public SiloPhysical (SpeedController motor, IRobot robot){
		logic = new SiloLogic(motor);
		this.robot = robot;
	}

	@Override
	public void disable() {
		logic.disable();
		
	}

	@Override
	public void setSpeed(double spd) {
		logic.setSpeed(spd);
	}

	@Override
	public double getSpeed() {
		return logic.getSpeed();
	}

	@Override
	protected void initDefaultCommand() {
	}

}
