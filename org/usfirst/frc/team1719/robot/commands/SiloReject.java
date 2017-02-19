package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.command.Command;

public class SiloReject extends Command {
	
	private final ISilo silo;

	public SiloReject(ISilo silo) {
		this.silo = silo;
	}
	
	public void execute() {
		silo.setSpeed(-0.5D);
	}
	
	protected void end() {
		silo.disable();
	}

    @Override
    protected boolean isFinished() {
        return false;
    }

}
