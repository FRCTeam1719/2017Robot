package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DepositGear extends Command {

	private Timer timer = new Timer();
	private IGearHandler handler;
	private final double DELAY = 3;

	public DepositGear(IGearHandler handler) {
		this.handler = handler;
	}

	@Override
	public void initialize() {
		timer.reset();
		timer.start();
		handler.open();
	}

	@Override
	public boolean isFinished() {
		return timer.get() >= DELAY;
	}

	@Override
	public void end() {
		timer.stop();
		handler.close();
	}

	@Override
	public void interrupted() {
		end();
	}

}
