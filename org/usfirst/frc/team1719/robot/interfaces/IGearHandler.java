package org.usfirst.frc.team1719.robot.interfaces;

public interface IGearHandler extends GenericSubsystem{

	enum gearStates {
			RECIEVE,
			GRAB,
			TRANSPORT,
			POSITION,
			DELIVER;
	}
	
	void setState(gearStates state);
	
	gearStates getState();
	
	
	
}
