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
	private ICameraManager.Camera current;
	
	public FastCameraManagerLogic(){
		gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
		climberCamera = CameraServer.getInstance().startAutomaticCapture(1);
		server = CameraServer.getInstance().getVideo();
		//Dummy sinks to keep cams open
		gearSink = new CvSink("Gear");
		gearSink.setSource(gearCamera);
		gearSink.setEnabled(true);
		climberSink = new CvSink("Climber");
		climberSink.setSource(climberCamera);
		climberSink.setEnabled(true);
		setCamera(ICameraManager.Camera.GEAR);
	}
	
	@Override
	public void disable() {
		//No disable
	}

	@Override
	public void setCamera(Camera camera) {
		switch(camera){
		case GEAR:server.setSource(gearCamera);break;
		case CLIMBER:server.setSource(climberCamera);break;
		}
		current = camera;
	}

	@Override
	public Camera getCamera() {
		return current;
	}
	
	@Override
	public void toggleCamera(){
		switch(current){
		case GEAR: setCamera(ICameraManager.Camera.CLIMBER);break;
		case CLIMBER: setCamera(ICameraManager.Camera.GEAR);break;
		}
	}

}
