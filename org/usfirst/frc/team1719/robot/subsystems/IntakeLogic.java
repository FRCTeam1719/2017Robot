package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IIntake;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Logical implementation of the IIntake interface
 * @author Benny
 */
public class IntakeLogic implements IIntake{

	private SpeedController controller;
	public IntakeLogic(SpeedController controller){
		this.controller = controller;
	}
	/**
	 * calls on Disable to make sure the subsystem is safe
	 */
	@Override
	public void disable() {
		controller.disable();
	}
	@Override
	/**
	 * Sets the speed for the intake
	 */
	public void set(double speed) {
		controller.set(speed);
		
	}
	
	@Override
	public String toString() {
		return "Intake Logic";
	}
	
}
