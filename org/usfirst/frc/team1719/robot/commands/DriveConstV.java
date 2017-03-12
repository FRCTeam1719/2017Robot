package org.usfirst.frc.team1719.robot.commands;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
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
public class DriveConstV extends Command {
    
    private final PIDController leftController;
    private final PIDController rightController;
    private final IDrive drive; 
    
    private final double vLeft;
    private final double vRight;
    
    private final BooleanSupplier endif;
    
    private double leftMotorOutput = 0;
    private double rightMotorOutput = 0;
    
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
    
    public DriveConstV(double _vLeft, double _vRight, IRobot _robot, IDrive _drive, BooleanSupplier _endif) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        vLeft = _vLeft;
        vRight = _vRight;
        drive = _drive;
        endif = _endif;
        
        drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
        drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
        try {
            requires((Subsystem) drive);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
        }
        
        leftController = new PIDController(SmartDashboard.getNumber(UseDrive.LEFT_DRIVE_KP, 0.1), 0,
                SmartDashboard.getNumber(UseDrive.LEFT_DRIVE_KD, 0),
                SmartDashboard.getNumber(UseDrive.LEFT_DRIVE_KF, 0), drive.getEncoderL(),
                new leftDrivePIDOut());
        rightController = new PIDController(SmartDashboard.getNumber(UseDrive.LEFT_DRIVE_KP, 0.1), 0,
                SmartDashboard.getNumber(UseDrive.LEFT_DRIVE_KD, 0),
                SmartDashboard.getNumber(UseDrive.LEFT_DRIVE_KF, 0), drive.getEncoderR(),
                new rightDrivePIDOutput());
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
        drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
        
        leftController.setSetpoint(vLeft);
        rightController.setSetpoint(vRight);
        
        leftController.setOutputRange(-1, 1);
        rightController.setOutputRange(-1, 1);
        
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
        drive.moveTank(leftMotorOutput, rightMotorOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return endif.getAsBoolean();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
