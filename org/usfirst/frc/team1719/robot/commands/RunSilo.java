package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Constants;
import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RunSilo extends Command {

	private final ISilo silo;
	private boolean instantFail = true;
	private final IDashboard dashboard;

	public RunSilo(ISilo silo, IDashboard dashboard) {
		this.silo = silo;
		this.dashboard = dashboard;
		try {
			requires((Subsystem) silo);
		} catch (ClassCastException e) {
			System.out.println("Couldn't cast! Probably in a test!");
		}
	}

	public void initialize() {
		// Check if the shooter is running
		if (dashboard.getBoolean(Constants.SHOOTER_RUNNING, false)) {
			instantFail = false;
		}
		dashboard.putBoolean(Constants.SILO_RUNNING, true);
	}

	public void execute() {
		if (!instantFail) {
			silo.setSpeed(1.0D);
		}
	}

	@Override
	protected boolean isFinished() {
		return instantFail;
	}

	public void end() {
		silo.setSpeed(0D);
		dashboard.putBoolean(Constants.SILO_RUNNING, false);
	}
	
	public void interrupted(){
		end();
	}

}
