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
 * Default command used for controlling the drive
 * @author aaron
 *
 */
public class UseDrive extends Command {
    
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
    private boolean shifted = false;
    
    private double DRIVE_MAX_SPEED = 20;
    
    private final double JOYSTICK_DEADZONE = 0.15;
    
    double left_kP = 0;
    double left_kI = 0;
    double left_kD = 0;
    
    double right_kP = 0;
    double right_kI = 0;
    double right_kD = 0;
    
    double leftMotorOutput = 0;
    double rightMotorOutput = 0;
    
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
    
    /**
     * @param _drive Drive interface to control
     * @param _robot Robot to grab external data from
     */
    public UseDrive(IDrive _drive, IRobot _robot) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        drive = _drive;
        robot = _robot;
        oi = robot.getOI();
        
        drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
    	drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
        try {
            requires((Subsystem) drive);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
        }
        
        leftController = new PIDController(left_kP, left_kI, left_kD, drive.getEncoderL(), new leftDrivePIDOut());
    	rightController = new PIDController(right_kP, right_kI, right_kD, drive.getEncoderR(), new rightDrivePIDOutput());
    	
    	
        drive.shift(shifted);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
    	drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
    	
    	leftController.setOutputRange(-1, 1);
    	rightController.setOutputRange(-1, 1);
    	
    	double maxInput = DRIVE_MAX_SPEED * 1.5;
    	leftController.setInputRange(-(maxInput), maxInput);
    	rightController.setInputRange(-(maxInput), maxInput);
    	
    	leftController.setContinuous(false);
    	rightController.setContinuous(false);
    	
    	leftController.setToleranceBuffer(20);
    	rightController.setToleranceBuffer(20);
    	
    	leftController.setPercentTolerance(5);
    	rightController.setPercentTolerance(5);
    	
    	
    	leftController.enable();
    	rightController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	setPIDConstantsFromDashboard();
    	
        double leftJoystick = -oi.getLeftY(), rightJoystick = -oi.getRightY();
        double desiredLeftRate = leftJoystick * DRIVE_MAX_SPEED;
        double desiredRightRate = rightJoystick * DRIVE_MAX_SPEED;

        
        if (Math.abs(leftJoystick) < JOYSTICK_DEADZONE) {
        	leftMotorOutput = 0;
        	leftController.setSetpoint(0);
        	leftController.reset();
        	desiredLeftRate = 0;
        
        }
        else {
        	leftController.enable();
        	leftController.setSetpoint(desiredLeftRate);
        }
        
        if (Math.abs(rightJoystick) < JOYSTICK_DEADZONE) {
        	rightMotorOutput = 0;
        	rightController.setSetpoint(0);
        	rightController.reset();
        	desiredRightRate = 0;
        }
        else {
        	rightController.enable();
        	rightController.setSetpoint(desiredRightRate);
        }
        drive.moveTank(leftMotorOutput, rightMotorOutput);
        
        if(shifted != oi.getShifter()) {
            drive.shift(shifted = !shifted);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void setPIDConstantsFromDashboard() {
    	left_kP = SmartDashboard.getNumber(LEFT_DRIVE_KP);
    	left_kI = SmartDashboard.getNumber(LEFT_DRIVE_KI);
    	left_kD = SmartDashboard.getNumber(LEFT_DRIVE_KD);

    	right_kP = SmartDashboard.getNumber(RIGHT_DRIVE_KP);
    	right_kI = SmartDashboard.getNumber(RIGHT_DRIVE_KI);
    	right_kD = SmartDashboard.getNumber(RIGHT_DRIVE_KD);

    	leftController.setPID(left_kP, left_kI, left_kD);
    	rightController.setPID(right_kP, right_kI, right_kD);
    }
}
