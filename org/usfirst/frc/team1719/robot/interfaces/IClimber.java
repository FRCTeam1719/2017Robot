package org.usfirst.frc.team1719.robot.interfaces;

public interface IClimber extends GenericSubsystem {
	
	void setSpeed(double speed);
	double getSpeed();
	
	double getRate();

}