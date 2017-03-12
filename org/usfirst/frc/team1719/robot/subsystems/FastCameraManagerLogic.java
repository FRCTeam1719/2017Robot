package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ICameraManager;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;

public class FastCameraManagerLogic implements ICameraManager{

	private UsbCamera gearCamera;
	private UsbCamera gearWideAngleCamera;
	private CvSink gearSink;
	private CvSink climberSink;
	private VideoSink server;
	private ICameraManager.Camera current;
	
	public FastCameraManagerLogic(){
		gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
		gearWideAngleCamera = CameraServer.getInstance().startAutomaticCapture(1);
		server = CameraServer.getInstance().getServer();
		//Dummy sinks to keep cams open
		gearSink = new CvSink("Gear");
		gearSink.setSource(gearCamera);
		gearSink.setEnabled(true);
		climberSink = new CvSink("Climber");
		climberSink.setSource(gearWideAngleCamera);
		climberSink.setEnabled(true);
		setCamera(ICameraManager.Camera.GEAR_WIDE);
	}
	
	@Override
	public void disable() {
		//No disable
	}

	@Override
	public void setCamera(Camera camera) {
		switch(camera){
		case GEAR:server.setSource(gearCamera);break;
		case GEAR_WIDE:server.setSource(gearWideAngleCamera);break;
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
		case GEAR: setCamera(ICameraManager.Camera.GEAR_WIDE);break;
		case GEAR_WIDE: setCamera(ICameraManager.Camera.GEAR);break;
		}
	}

}
