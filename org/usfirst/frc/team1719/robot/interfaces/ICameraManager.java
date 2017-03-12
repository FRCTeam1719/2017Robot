package org.usfirst.frc.team1719.robot.interfaces;

public interface ICameraManager extends GenericSubsystem{

	enum Camera {
		GEAR,
		GEAR_WIDE;
	}
	
	void setCamera(Camera camera);
	Camera getCamera();
	void toggleCamera();

}
