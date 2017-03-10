package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ICameraManager;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;

public class AgressiveCameraManagerLogic implements ICameraManager{
	
	UsbCamera gearCamera;
	UsbCamera climberCamera;
	VideoSink server;
	ICameraManager.Camera current;
	
	public AgressiveCameraManagerLogic(){
		gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
		climberCamera = CameraServer.getInstance().startAutomaticCapture(1);
		server = CameraServer.getInstance().getServer();
		setActiveCamera(ICameraManager.Camera.GEAR);
	}

	@Override
	public Camera getActiveCamera() {
		return current;
	}

	@Override
	public void setActiveCamera(Camera active) {
		switch(active){
		case GEAR: server.setSource(gearCamera);break;
		case CLIMBER: server.setSource(climberCamera);break;
		}
		current = active;
	}

	@Override
	public void disable() {
		//No disable
	}

}
