package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ICameraManager;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraManagerPhysical extends Subsystem implements ICameraManager{
	
	private ICameraManager logic;
	
	public CameraManagerPhysical(ICameraManager logic){
		this.logic = logic;
	}

	@Override
	public void disable() {
		logic.disable();
	}

	@Override
	public void setCamera(Camera camera) {
		logic.setCamera(camera);
	}

	@Override
	public Camera getCamera() {
		return logic.getCamera();
	}
	
	@Override
	public void toggleCamera(){
		logic.toggleCamera();
	}

	@Override
	protected void initDefaultCommand() {
		//No default;
	}

}
