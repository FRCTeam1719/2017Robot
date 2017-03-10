package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ICameraManager;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraManagerPhysical extends Subsystem implements ICameraManager{
	
	private ICameraManager logic;
	
	public CameraManagerPhysical(ICameraManager logic){
		this.logic = logic;
	}

	@Override
	public Camera getActiveCamera() {
		return logic.getActiveCamera();
	}

	@Override
	public void setActiveCamera(Camera active) {
		logic.setActiveCamera(active);
	}

	@Override
	protected void initDefaultCommand() {
		//No defualt command
	}

	@Override
	public void disable() {
		//No disable
	}

}
