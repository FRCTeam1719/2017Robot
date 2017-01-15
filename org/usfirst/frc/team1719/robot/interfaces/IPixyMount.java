package org.usfirst.frc.team1719.robot.interfaces;

public interface IPixyMount extends GenericSubsystem{

	void setX(double x);
	void setY(double y);
	
	double getX();
	double getY();
}
