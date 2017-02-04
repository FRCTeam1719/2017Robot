
package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IClimber;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberPhysical extends Subsystem implements IClimber{
	
	ClimberLogic logic;
	
	public ClimberPhysical(SpeedController motor, IEncoder encoder) {
		logic = new ClimberLogic(motor, encoder);
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
	public double getRate() {
		return logic.getRate();
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
