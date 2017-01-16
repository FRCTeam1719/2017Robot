package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalExShooter extends Subsystem implements IExShooter{

	LogicalExShooter logic;
	IRobot robot;
	public PhysicalExShooter (SpeedController motor, IRobot robot, IEncoder enc){
		logic = new LogicalExShooter(motor, enc);
		this.robot = robot;
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
		setDefaultCommand(new UseExShooter(this, robot));
	}


	@Override
	public IEncoder getEncoder() {
		return logic.getEncoder();
	}

	

}
