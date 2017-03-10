package org.usfirst.frc.team1719.robot.interfaces;

public interface ICameraManager extends GenericSubsystem{
	
	enum Camera { 
		GEAR,
		CLIMBER
	}

	Camera getActiveCamera();
	void setActiveCamera(Camera active);
	
}
