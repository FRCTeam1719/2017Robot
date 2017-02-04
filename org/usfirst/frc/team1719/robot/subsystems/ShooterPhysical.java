package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterPhysical extends Subsystem implements IExShooter{

	ShooterLogic logic;
	IRobot robot;
	public ShooterPhysical (SpeedController motor, IRobot robot, IEncoder enc1, IEncoder enc2){
		logic = new ShooterLogic(motor, enc1, enc2);
		this.robot = robot;
	}
	
	
	@Override
	public void disable() {
		logic.disable();
		
	}

	@Override
	public void setSpeed(double speed) {
		logic.setSpeed(speed);
		
	}

	@Override
	public double getSpeed() {
		return logic.getSpeed();
	}

	@Override
	protected void initDefaultCommand() {
		//No default command
		setDefaultCommand(new UseExShooter(this, robot));
	}


	@Override
	public IEncoder getEncoder1() {
		return logic.getEncoder1();
	}
	
	public IEncoder getEncoder2() {
		return logic.getEncoder2();
	}


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		logic.setPIDSourceType(pidSource);
	}


	@Override
	public PIDSourceType getPIDSourceType() {
		return logic.getPIDSourceType();
	}


	@Override
	public double pidGet() {
		return logic.pidGet();
	}



	@Override
	public double getAvgEncoderRate() {
		return logic.getAvgEncoderRate();
	}


	@Override
	public double getAvgEncoderDistance() {
		return logic.getAvgEncoderDistance();
	}

	

}
