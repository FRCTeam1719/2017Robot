package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.SpeedController;

public class SiloLogic implements ISilo {
	
    private static final double OFF = 0.0D;
	private SpeedController motor;
	
	public SiloLogic(SpeedController _motor){
		motor = _motor;
	}

	@Override
	public void disable() {
	    motor.set(OFF);
	}

	@Override
	public void setSpeed(double spd) {
		motor.set(spd);
	}

	@Override
	public double getSpeed() {
		return motor.get();
	}

}
