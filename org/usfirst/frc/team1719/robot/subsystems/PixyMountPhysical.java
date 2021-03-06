package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.SimpleUsePixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.vision.SingleTarget;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PixyMountPhysical extends Subsystem implements IPixyMount{
	
	PixyMountLogic logic;
	IPixy camera;
	
	public PixyMountPhysical (Servo pan, Servo tilt, IPixy camera){
		this.camera = camera;
		logic = new PixyMountLogic(pan, tilt);
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
		setDefaultCommand(new SimpleUsePixyMount(camera, this, new SingleTarget()));
	}

	@Override
	public double getX() {
		return logic.getX();
	}

	@Override
	public double getY() {
		return logic.getY();
	}
	
	@Override
	public String toString() {
		return "Pixy Mount Subsys";
	}

}
