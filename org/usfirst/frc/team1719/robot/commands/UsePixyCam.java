package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IPixyCam;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class UsePixyCam extends Command{

	private IPixyCam pixyCam;
	
	private double kP;
	private double kI;
	private double kD;
	
	public static String pString = "PixyCam P constant (x): ";
	public static String kString = "PixyCam I constant (x): ";
	public static String iString = "PixyCam D constant (x): ";
	
	private PIDController xPID;
	
	public UsePixyCam(IPixyCam pixyCam) {
		this.pixyCam = pixyCam;
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
