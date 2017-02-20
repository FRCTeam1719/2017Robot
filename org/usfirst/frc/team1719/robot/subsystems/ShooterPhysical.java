package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterPhysical extends Subsystem implements IExShooter{

	ShooterLogic logic;
	IRobot robot;
	public ShooterPhysical (SpeedController motor, IRobot robot, IEncoder enc1){
		logic = new ShooterLogic(motor, enc1);
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
		//setDefaultCommand(new UseShooter(this, robot));
	}


	@Override
	public IEncoder getEncoder() {
		return logic.getEncoder();
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
	public double getEncoderRate() {
		return logic.getEncoderRate();
	}


	@Override
	public double getEncoderDistance() {
		return logic.getEncoderDistance();
	}

	@Override
	public String toString() {
		return "Shooter Subsystem";
	}
}
