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
public class TurnToHeading extends Command implements PIDSource, PIDOutput {

    private static final double TOLERANCE = 2.0D;
    
    private final double targetHeading;
    private final IPositionTracker posTracker;
    private final IDrive drive;
    private final IRobot robot;
    private final IOI oi;
    
    private PIDController pid;
    
    private double pidout;
    
    public TurnToHeading(double _targetHeading, IPositionTracker _posTracker, IDrive _drive, IRobot _robot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        targetHeading = _targetHeading;
        posTracker = _posTracker;
        robot = _robot;
        drive = _drive;
        oi = robot.getOI();
        try {
            requires( (Subsystem) drive);
        }
        catch (ClassCastException e) {
            System.out.println("Running Unit test on TurnToHeading");
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pid = new PIDController(SmartDashboard.getNumber("TurnToHeading K[P]", 0.1),
                SmartDashboard.getNumber("TurnToHeading K[I]", 0),
                SmartDashboard.getNumber("TurnToHeading K[D]", 0), this, this);
        pid.setInputRange(-180.0D, 180.0D);
        pid.setContinuous();
        pid.setOutputRange(-1.0D, 1.0D);
        pid.setSetpoint(targetHeading);
        pid.setAbsoluteTolerance(TOLERANCE);
        pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!pid.isEnabled()) pid.enable();
        drive.moveTank(-pidout, pidout);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget() || oi.getAbortAutomove();
    }

    // Called once after isFinished returns true
    protected void end() {
        pid.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    @Override
    public void pidWrite(double output) {
        pidout = output;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        return posTracker.getHeading();
    }
}
