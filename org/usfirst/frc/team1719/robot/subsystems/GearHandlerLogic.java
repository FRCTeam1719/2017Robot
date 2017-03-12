package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;

public class GearHandlerLogic implements IGearHandler {

	private ISolenoid door;
	private ISolenoid flap;
	private boolean flapState;

	public GearHandlerLogic(ISolenoid door, ISolenoid flap) {
		this.door = door;
		this.flap = flap;
		flapState = false;
	}

	@Override
	public void open() {
		door.set(true);
	}

	@Override
	public void close() {
		door.set(false);
	}

	@Override
	public void disable() {
		door.set(false);
		door.set(false);
	}

	@Override
	public void setFlap(boolean state) {
		flapState = state;
		flap.set(state);
	}

	@Override
	public void toggleFlap() {
		flapState = !flapState;
		setFlap(flapState);
	}
	
	@Override
	public boolean getFlapState(){
		return flapState;
	}

}
