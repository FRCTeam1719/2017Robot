package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;

public class GearHandlerLogic implements IGearHandler {

	private ISolenoid solenoid;

	public GearHandlerLogic(ISolenoid solenoid) {
		this.solenoid = solenoid;
	}

	@Override
	public void open() {
		solenoid.set(true);
	}

	@Override
	public void close() {
		solenoid.set(false);
	}

	@Override
	public void disable() {
		solenoid.set(false);
	}

}
