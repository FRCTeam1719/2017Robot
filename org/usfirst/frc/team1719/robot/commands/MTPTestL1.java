package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MTPTestL1 extends Command implements PIDSource, PIDOutput {


    private static final double SPD = 0.5D;
    
    private IPositionTracker posTracker;
    private IDrive drive;
    private IRobot robot;
    private IOI oi;
    private PIDController rotateController;
    
    private double targetAngle;
    private double rotSpd;
    
    public MTPTestL1(IPositionTracker _posTracker,
            IDrive _drive, IRobot _robot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        posTracker = _posTracker;
        robot = _robot;
        drive = _drive;
        oi = robot.getOI();
        requires( (Subsystem) drive);
        SmartDashboard.putNumber("MTPLT1 target angle", targetAngle = 0.0D);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        rotateController = new PIDController(0, 0, 0, this, this);
        rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.02),
                SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                SmartDashboard.getNumber("MoveToPos K[1][D]", 0));
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D,  180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
        rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(oi.getResetPIDConstants()) {
            double p = SmartDashboard.getNumber("MoveToPos K[1][P]", 0.02);
            double i = SmartDashboard.getNumber("MoveToPos K[1][I]", 0);
            double d = SmartDashboard.getNumber("MoveToPos K[1][D]", 0);
            rotateController.setPID(p, i, d);
            System.out.println("Updating PID constants: " + p + "/" + i + "/" + d);
        }
        targetAngle = SmartDashboard.getNumber("MTPLT1 target angle", targetAngle);
        drive.moveArcade(SPD, -rotSpd);
        SmartDashboard.putNumber("MTP Desired angle", targetAngle);
        SmartDashboard.putNumber("MTP current angle", posTracker.getHeading());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return oi.getAbortAutomove();
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
        rotSpd = output;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}

    @Override
    public double pidGet() {
        return posTracker.getHeading() - targetAngle;
    }
}
