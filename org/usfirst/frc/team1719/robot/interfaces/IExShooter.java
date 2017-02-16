package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.PIDSource;

public interface IExShooter extends GenericSubsystem, PIDSource {
	void setSpeed(double speed);
	double getSpeed();
	
	IEncoder getEncoder1();
	
	double getAvgEncoderRate();
	double getAvgEncoderDistance();
	
}
