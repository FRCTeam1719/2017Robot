package org.usfirst.frc.team1719.robot.interfaces;

public interface IGearHandler extends GenericSubsystem{

	void open();

	void close();

	void setFlap(boolean state);
	
	void toggleFlap();
	
	boolean getFlapState();
}
