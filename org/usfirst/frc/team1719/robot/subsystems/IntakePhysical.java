package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IIntake;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * LogicIntake Wrapper
 * @author Benny
 */
public class IntakePhysical extends Subsystem implements IIntake{

	private IntakeLogic logic;
	public IntakePhysical(SpeedController controller) {
		logic = new IntakeLogic(controller);
	}
	@Override
	protected void initDefaultCommand() {
		
	}

	@Override
	public void disable() {
		logic.disable();
	}
	/**
	 * @param speed of intake
	 */
	@Override
	public void set(double speed) {
		logic.set(speed);
	}

	@Override
	public String toString() {
		return "Intake Subsystem";
	}
	
}
