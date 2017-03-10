package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ICameraManager;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;

public class FastCameraManagerLogic implements ICameraManager{
	
	private UsbCamera gearCamera;
	private UsbCamera climberCamera;
	private CvSink gearSink;
	private CvSink climberSink;
	private VideoSink server;
	private ICameraManager.Camera currentCamera;
	
	public FastCameraManagerLogic(){
		gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
		climberCamera = CameraServer.getInstance().startAutomaticCapture(0);
		server = CameraServer.getInstance().getServer();
		//Dummy sinks to keep cameras open
		gearSink = new CvSink("GearCamera");
		gearSink.setSource(gearCamera);
		gearSink.setEnabled(true);
		climberSink = new CvSink("ClimberCamera");
		climberSink.setSource(climberCamera);
		climberSink.setEnabled(true);
		//Set the default camera
		setActiveCamera(ICameraManager.Camera.GEAR);
	}


	@Override
	public Camera getActiveCamera() {
		return currentCamera;
	}

	@Override
	public void setActiveCamera(Camera active) {
		switch(active){
		case GEAR: server.setSource(gearCamera);break;
		case CLIMBER: server.setSource(climberCamera);break;
		}
		currentCamera = active;
	}

	@Override
	public void disable() {
		//No disable
	}

}
