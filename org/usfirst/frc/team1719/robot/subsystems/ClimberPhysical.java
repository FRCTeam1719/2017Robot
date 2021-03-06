
package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IClimber;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberPhysical extends Subsystem implements IClimber{
	
	ClimberLogic logic;
	
	public ClimberPhysical(SpeedController motor, IEncoder encoder, DigitalInput limit1, DigitalInput limit2) {
		logic = new ClimberLogic(motor, encoder, limit1, limit2);
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
	
	@Override
	public String toString(){
		return "Climber Subsystem";
	}

}
