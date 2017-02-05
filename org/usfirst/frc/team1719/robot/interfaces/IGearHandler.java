package org.usfirst.frc.team1719.robot.interfaces;

public interface IGearHandler extends GenericSubsystem{

	enum gearStates {
			RECIEVE,
			GRAB,
			TRANSPORT,
			POSITION,
			DELIVER;
	}
	
	static IGearHandler.gearStates[] order = 
		{IGearHandler.gearStates.RECIEVE,IGearHandler.gearStates.GRAB,IGearHandler.gearStates.TRANSPORT,
				IGearHandler.gearStates.POSITION, IGearHandler.gearStates.DELIVER};

	
	void setState(gearStates state);
	
	gearStates getState();
	
	
	
}
