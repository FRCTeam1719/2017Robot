package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UseDrive extends Command implements PIDOutput {
    
	public static final String LEFT_DRIVE_KP = "Left Drive kP: ";
	public static final String LEFT_DRIVE_KI = "Left Drive kI: ";
	public static final String LEFT_DRIVE_KD = "Left Drive kD: ";
	
	public static final String RIGHT_DRIVE_KP = "Right Drive kP: ";
	public static final String RIGHT_DRIVE_KI = "Right Drive kI: ";
	public static final String RIGHT_DRIVE_KD = "Right Drive kD: ";
	PIDController leftController;
	PIDController rightController;
    private final IRobot robot;
    private final IOI oi;
    private final IDrive drive;
    private boolean isTest;
    private boolean shifted = false;
    private double MAX_DRIVE_SPEED = 5;
    
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;
    private double kF = 0;
    
    PIDController velocityController;
    
    double leftMotorOutput = 0;
    double rightMotorOutput = 0;
    
    final double DEADZONE_TOLERANCE = 0.1;
    
    private class leftDrivePIDOut implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			leftMotorOutput = output;
		}
    	
    }
    
    private class rightDrivePIDOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			rightMotorOutput = output;
		}
    	
    }
    
    public UseDrive(IDrive _drive, IRobot _robot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        drive = _drive;
        robot = _robot;
        oi = robot.getOI();
        try {
            requires((Subsystem) drive);
            isTest = false;
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
            isTest = true;
        }
        velocityController = new PIDController(kP, kI, kD, drive.getNavX(), this);
        drive.shift(shifted);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.getNavX().setPIDSourceType(PIDSourceType.kRate);
    	
    	
    	velocityController.setOutputRange(-.5, .5);
    	
    	velocityController.setToleranceBuffer(5);
    	
    	velocityController.enable();
    	velocityController.setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	setPIDConstantsFromDashboard();
    	double leftJoystick = oi.getLeftY(), rightJoystick = oi.getRightY();
    	//System.out.println("Left: " + leftJoystick);
        if (Math.abs(leftJoystick) < DEADZONE_TOLERANCE) {
        	leftJoystick = 0;
        	drive.moveTank(0, 0);
        	velocityController.setSetpoint(0);
        	
        	velocityController.reset();
        }
        else {
        	velocityController.enable();
        	velocityController.setSetpoint(leftJoystick);
        
        	drive.moveTank(leftMotorOutput, rightMotorOutput);
        }
        
        if(shifted != oi.getShifter()) {
            drive.shift(shifted = !shifted);
            System.out.println("Shifting");
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.moveTank(0, 0);
    	velocityController.setSetpoint(0);
    	velocityController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.moveTank(0, 0);
    	velocityController.setSetpoint(0);
    	velocityController.disable();
    }
    
    public void setPIDConstantsFromDashboard() {
    	velocityController.setPID(SmartDashboard.getNumber(LEFT_DRIVE_KP, 0), SmartDashboard.getNumber(LEFT_DRIVE_KI, 0), SmartDashboard.getNumber(LEFT_DRIVE_KD, 0));

    }

	@Override
	public void pidWrite(double output) {
		rightMotorOutput = output;
		leftMotorOutput = output;
	}
}
