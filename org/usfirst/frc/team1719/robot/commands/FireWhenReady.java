package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FireWhenReady extends Command{

	private final IExShooter shooter;
	private final ISilo silo;
	private final double DESIRED_RATE;
	private final double ALLOWED_VARIANCE = 10; //TODO work this out
	
	public FireWhenReady(IExShooter shooter, ISilo silo){
		this.shooter = shooter;
		this.silo = silo;
		DESIRED_RATE = SmartDashboard.getNumber("Desired RevUpShooter speed (RPS): ", Double.POSITIVE_INFINITY);
	}
	
	@Override
	public void execute(){
		double variance = Math.abs(DESIRED_RATE - shooter.getEncoderRate());
		if(variance < ALLOWED_VARIANCE){
			silo.setSpeed(1);
		}else{
			silo.setSpeed(0);
		}
		
	}
	
	@Override
	public boolean isFinished(){
		return false;
	}
	
	@Override
	public void end(){
		silo.setSpeed(0);
	}
	
	@Override
	public void interrupted(){
		end();
	}

}
