package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalExShooter extends Subsystem implements IExShooter{

	LogicalExShooter logic;
	
	public PhysicalExShooter (SpeedController motor){
		logic = new LogicalExShooter(motor);
	}
	
	
	@Override
	public void disable() {
		logic.disable();
		
	}

	@Override
	public void setSpeed(double speed) {
		logic.setSpeed(speed);
		
	}

	@Override
	public double getSpeed() {
		return logic.getSpeed();
	}

	@Override
	protected void initDefaultCommand() {
		//No default command
		
	}

	

}
