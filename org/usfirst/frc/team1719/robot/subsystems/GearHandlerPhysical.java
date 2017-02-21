package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHandlerPhysical extends Subsystem implements IGearHandler {

	private GearHandlerLogic logic;

	public GearHandlerPhysical(ISolenoid solenoid) {
		logic = new GearHandlerLogic(solenoid);
	}

	@Override
	public void open() {
		logic.open();
	}

	@Override
	public void close() {
		logic.close();
	}

	@Override
	public void disable() {
		logic.disable();
	}

	@Override
	public void initDefaultCommand() {
		// No default command
	}

}
