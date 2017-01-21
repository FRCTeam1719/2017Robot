package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RevUpShooter extends Command implements PIDOutput {
	
	public static final String SHOOTER_KP = "Shooter kP: ";
	public static final String SHOOTER_KI = "Shooter kI: ";
	public static final String SHOOTER_KD = "Shooter kD: ";
	public static final String SHOOTER_KF = "Shooter kF: ";

	
	private double kP;
	private double kI;
	private double kD;
	private double kF;
	
	double desiredRate = 0;
	
	private final IExShooter exShooter;
	private final IOI oi;
	private final IRobot robot;
	private final double MAX_SPEED = 50;
	private final double MAX_SPEED_LIMIT_SCALING = 1.2D; // Multiply max_speed by this to give some leeway in the input range
	
	private final int TOLERANCE_BUFFER_SIZE = 3; // Increase to reduce measurement noise, decrease to increase reactivity
	private final double PERCENT_TOLERANCE = 1.25; // percent of MAX_SPEED * MAX_SPEED_LIMIT_SCALING
	double motorOutput;
	
	private PIDController velocityController;
	
	/**
	 * @author Kyle
	 * 
	 * A command for revving the shooter up to speed
	 * @param shooter The subsystem to work with
	 * @param robot The robot to work with
	 * @param desiredRate The desired rate for the shooter wheel
	 */
    public RevUpShooter(IExShooter shooter, IRobot robot, double desiredRate) {
        this.desiredRate = desiredRate;
    	oi = robot.getOI();
    	this.robot = robot;
    	exShooter = shooter;
    	try {
    		requires( (Subsystem) shooter);
    	}
    	catch (ClassCastException e) {
    		System.out.println("Running unit test on RevUpShooter");
    	}
    	
    	velocityController = new PIDController(kP, kI, kD, kF, shooter, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	velocityController.setOutputRange(-1, 1);
    	velocityController.setInputRange(-MAX_SPEED * MAX_SPEED_LIMIT_SCALING, MAX_SPEED * MAX_SPEED_LIMIT_SCALING);
    	velocityController.setToleranceBuffer(TOLERANCE_BUFFER_SIZE);
    	velocityController.setPercentTolerance(PERCENT_TOLERANCE);
    	velocityController.setContinuous(false);
    	
    	velocityController.setSetpoint(desiredRate);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setPIDConstantsFromDashboard();
    	
    	exShooter.setSpeed(motorOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return oi.getRevUpShooter();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void pidWrite(double output) {
		this.motorOutput = output;
	}
	
	public void setPIDConstantsFromDashboard() {
		kP = robot.getDashboard().getNumber(SHOOTER_KP, 0);
		kI = robot.getDashboard().getNumber(SHOOTER_KI, 0);
		kD = robot.getDashboard().getNumber(SHOOTER_KD, 0);
		kF = robot.getDashboard().getNumber(SHOOTER_KF, 0);
		
		velocityController.setPID(kP, kI, kD, kF);
	}
}
