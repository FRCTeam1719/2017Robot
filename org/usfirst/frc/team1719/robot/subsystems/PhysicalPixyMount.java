package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.SimpleUsePixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.VisionTarget;
import org.usfirst.frc.team1719.robot.vision.TwoFuelUnits;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalPixyMount extends Subsystem implements IPixyMount{
	
	LogicalPixyMount logic;
	IPixy camera;

	
	public PhysicalPixyMount (Servo pan, Servo tilt, IPixy camera, VisionTarget target){
		this.camera = camera;
		logic = new LogicalPixyMount(pan, tilt, target);
	}
	
	public PhysicalPixyMount(Servo pan, Servo tilt, IPixy camera){
		this(pan,tilt,camera,new TwoFuelUnits());
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
	
	public void setTarget(VisionTarget newTarget){
		logic.setTarget(newTarget);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SimpleUsePixyMount(camera, this, logic.getTarget()));
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
	public VisionTarget getTarget() {
		return logic.getTarget();
	}

}
