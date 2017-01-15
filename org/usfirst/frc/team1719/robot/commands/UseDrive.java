package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

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
    private double MAX_ENCODER_RATE = 5;
    
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
        try {
            requires((Subsystem) drive);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
        }
        drive.shift(shifted);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
    	drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
    	
    	leftController = new PIDController(left_kP, left_kI, left_kD, drive.getEncoderL(), new leftDrivePIDOut());
    	rightController = new PIDController(right_kP, right_kI, right_kD, drive.getEncoderR(), new rightDrivePIDOutput());
    	
    	leftController.setOutputRange(-1, 1);
    	rightController.setOutputRange(-1, 1);
    	
    	leftController.setInputRange(-(MAX_ENCODER_RATE ), MAX_ENCODER_RATE);
    	rightController.setInputRange(-(MAX_ENCODER_RATE), MAX_ENCODER_RATE);
    	
    	leftController.setContinuous(false);
    	rightController.setContinuous(false);
    	
    	leftController.setToleranceBuffer(20);
    	rightController.setToleranceBuffer(20);
    	
    	leftController.setPercentTolerance(5);
    	rightController.setAbsoluteTolerance(5);
    	
    	
    	leftController.enable();
    	rightController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
        double leftJoystick = oi.getLeftY(), rightJoystick = oi.getRightY();
        double desiredleftRate = leftJoystick * MAX_ENCODER_RATE;
        double desiredRightRate = rightJoystick * MAX_ENCODER_RATE;
        
        leftController.setSetpoint(desiredleftRate);
        rightController.setSetpoint(desiredRightRate);
        
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
    	
    }
}
