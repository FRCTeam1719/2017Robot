package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets the shooter to go a speed
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

	private final IExShooter shooter;
	private final double MAX_SPEED = 100000;
	private final double MAX_SPEED_LIMIT_SCALING = 1.2D; // Multiply max_speed
															// by this to give
															// some leeway in
															// the input range

	private final int TOLERANCE_BUFFER_SIZE = 3; // Increase to reduce
													// measurement noise,
													// decrease to increase
													// reactivity
	private final double PERCENT_TOLERANCE = 1.25; // percent of MAX_SPEED *
													// MAX_SPEED_LIMIT_SCALING
	double motorOutput;

	Timer timer = new Timer();

	private PIDController velocityController;
	
	private IRobot robot;

	/**
	 * @author Kyle
	 * 
	 *         A command for revving the shooter up to speed
	 * @param shooter
	 *            The subsystem to work with
	 * @param robot
	 *            The robot to work with
	 * @param desiredRate
	 *            The desired rate for the shooter wheel
	 */
    public RevUpShooter(IExShooter shooter, IRobot robot, double desiredRate) {
        this.desiredRate = desiredRate;
    	this.shooter = shooter;
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
    	desiredRate = robot.getDashboard().getNumber("Desired RevUpShooter speed (RPS): ", 0);
    	velocityController.setOutputRange(-1, 1);
    	velocityController.setInputRange(-MAX_SPEED * MAX_SPEED_LIMIT_SCALING, MAX_SPEED * MAX_SPEED_LIMIT_SCALING);
    	velocityController.setToleranceBuffer(TOLERANCE_BUFFER_SIZE);
    	velocityController.setPercentTolerance(PERCENT_TOLERANCE);
    	velocityController.setContinuous(false);
    	
    	velocityController.setSetpoint(desiredRate);
    	
    	velocityController.enable();
    	timer.start();
    	shooter.getEncoder().reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setPIDConstantsFromDashboard();
    	
    	shooter.setSpeed(motorOutput);
    	
    	if (timer.get() % 1 < 0.05) {
    		System.out.println("Encoder dist: " + shooter.getEncoder().getDistance());
    	}
    	
    	SmartDashboard.putNumber("Shooter target velocity (rpm)", desiredRate * 15.0D);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() >= 0.05 && robot.getOI().getRevUpShooter());
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
		kP = SmartDashboard.getNumber(SHOOTER_KP, 0);
		kD = SmartDashboard.getNumber(SHOOTER_KD, 0);
		kF = SmartDashboard.getNumber(SHOOTER_KF, 0);
		velocityController.setPID(kP, 0, kD, kF);
	}
}
