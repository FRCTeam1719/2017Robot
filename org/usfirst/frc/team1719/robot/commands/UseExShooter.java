package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Simple command for managing the Experimental Shooter
 * Control Scheme: While held, sets the motor to square of the joystick
 * @author jess
 *
 */
public class UseExShooter extends Command implements PIDOutput {

	public static final String SHOOTER_KP = "Shooter kP: ";
	public static final String SHOOTER_KI = "Shooter kI: ";
	public static final String SHOOTER_KD = "Shooter kD: ";

	
	private double kP;
	private double kI;
	private double kD;
	
	private final IExShooter exshooter;
	private final IOI oi;
	private final double DEADZONE_TOLERANCE = 0.05;
	private final double MAX_SPEED = 50;
	double motorOutput;
	
	private PIDController velocityController;
	
	public UseExShooter(IExShooter exshooter, IRobot robot) {
		this.exshooter = exshooter;
		oi = robot.getOI();
		try {
            requires((Subsystem) exshooter);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
        }
		
		velocityController = new PIDController(kP, kI, kD, exshooter.getEncoder(), this);
		
	}
	
	@Override
	protected void initialize() {
		exshooter.getEncoder().setPIDSourceType(PIDSourceType.kRate);
		velocityController.setInputRange(-(MAX_SPEED * 1.5), MAX_SPEED * 1.5);
		velocityController.setOutputRange(-1, 1);
		velocityController.setContinuous(false);
		velocityController.setPercentTolerance(2);
		velocityController.setToleranceBuffer(3);
	}
	
	
	/**
	 * Squares Joystick value while preserving sign of the original value
	 */
	
	@Override
	public void execute(){
		double joystickvalue = Math.abs(oi.getDeviceY()) * oi.getDeviceY(); 
		double desiredRate = joystickvalue * MAX_SPEED;

		if (Math.abs(joystickvalue) < DEADZONE_TOLERANCE){
			joystickvalue = 0;
			desiredRate = 0;
			velocityController.setSetpoint(0);
		}
		else {
			velocityController.enable();
			velocityController.setSetpoint(desiredRate);
		}
		
		exshooter.setSpeed(motorOutput);
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void pidWrite(double output) {
		this.motorOutput = output;
		
	}

}
