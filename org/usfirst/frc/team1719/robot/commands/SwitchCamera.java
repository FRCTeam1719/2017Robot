package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.ICameraManager;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SwitchCamera extends InstantCommand{
	
	private ICameraManager manager;

	public SwitchCamera(ICameraManager manager){
		this.manager = manager;
	}
	
	@Override
	public void execute(){
		switch(manager.getActiveCamera()){
		case GEAR: manager.setActiveCamera(ICameraManager.Camera.CLIMBER);break;
		case CLIMBER: manager.setActiveCamera(ICameraManager.Camera.GEAR);break;
		}
	}

}
