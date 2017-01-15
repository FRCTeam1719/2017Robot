package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UsePixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.IServo;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalPixyMount extends Subsystem implements IPixyMount{
	
	LogicalPixyMount logic;
	IRobot robot;
	
	public PhysicalPixyMount (IServo pan, IServo tilt, IRobot robot){
		logic = new LogicalPixyMount(pan, tilt);
		this.robot = robot;
	}

	@Override
	public void disable() {
		//Nothing to disable
	}

	@Override
	public void setX(double angleX) {
		logic.setX(angleX);
		
	}

	@Override
	public void setY(double angleY) {
		logic.setY(angleY);
		
	}

	@Override
	public double getAngleX() {
		return logic.getAngleX();
	}

	@Override
	public double getAngleY() {
		return logic.getAngleY();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UsePixyMount(this, robot));
	}

}
