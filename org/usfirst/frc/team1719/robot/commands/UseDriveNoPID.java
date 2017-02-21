package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UseDriveNoPID extends Command {
	
	IOI oi;
	IDrive drive;
	boolean shifted = false;
	public UseDriveNoPID(IOI oi, IDrive drive) {
		this.oi = oi;
		this.drive = drive;
		
		try {
			requires( (Subsystem) drive);
		}
		catch (ClassCastException e) {
			System.out.println("UNIT test on useDriveNoPID");
		}

	}
	public void initialize() {
		drive.shift(shifted);
	}
	
	
	public void execute() {
		System.out.println("Left: " + drive.getEncoderL().getDistance());
		System.out.println("Right: " + drive.getEncoderR().getDistance());
		double right = oi.getLeftY() * Math.pow(Math.abs(oi.getLeftY()), 2);
		double left = oi.getRightY() * Math.pow(Math.abs(oi.getRightY()), 2);
		
		if(shifted != oi.getShifter()) {
            drive.shift(shifted = !shifted);
        }
		drive.moveTank(left, right);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
