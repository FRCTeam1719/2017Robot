package org.usfirst.frc.team1719.robot.interfaces;

public interface ICameraManager extends GenericSubsystem{

	enum Camera {
		GEAR,
		CLIMBER;
	}
	
	void setCamera(Camera camera);
	Camera getCamera();
	void toggleCamera();

}
