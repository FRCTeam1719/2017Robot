package org.usfirst.frc.team1719.robot.interfaces;

import org.usfirst.frc.team1719.robot.sensors.IEncoder;

public interface IExShooter extends GenericSubsystem{
	void setSpeed(double speed);
	double getSpeed();
	
	IEncoder getEncoder();
}
