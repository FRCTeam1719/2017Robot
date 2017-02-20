package org.usfirst.frc.team1719.robot.commands;

import java.util.ArrayDeque;
import java.util.Queue;

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

    private static final int MOV_AVG_COUNT = 50;
    
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
        pid = new PIDController(SmartDashboard.getNumber("TurnToHeading K[P]", 0.007),
                SmartDashboard.getNumber("TurnToHeading K[I]", 0.0005),
                SmartDashboard.getNumber("TurnToHeading K[D]", 0), this, this) {
            /* Hack -- use RMSE for onTarget */
            @Override
            protected void calculate() {
                _updateRMSE_hack();
                super.calculate();
            }
            @Override
            public synchronized double getAvgError() {
                return _getRMSE_hack();
            }
        };
        pid.setInputRange(-180.0D, 180.0D);
        pid.setContinuous();
        pid.setOutputRange(-1.0D, 1.0D);
        pid.setSetpoint(targetHeading);
        pid.setAbsoluteTolerance(TOLERANCE);
        pid.setToleranceBuffer(MOV_AVG_COUNT);
        pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Use low gear
        drive.shift(true);
        if(true) {//oi.getResetPIDConstants()) {
            pid.setPID(SmartDashboard.getNumber("TurnToHeading K[P]", 0.1),
                    SmartDashboard.getNumber("TurnToHeading K[I]", 0),
                    SmartDashboard.getNumber("TurnToHeading K[D]", 0));
        }
        if(!pid.isEnabled()) {
            pid.enable();
        }
        System.out.println("Turn to heading power: " + pidout);
        SmartDashboard.putNumber("TTH err", pid.getError());
        drive.moveTank(pidout, -pidout);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean abort = oi.getAbortAutomove();
        if(abort) System.out.println("Driver abort");
        return pid.onTarget() || abort;
    }

    // Called once after isFinished returns true
    protected void end() {
        pid.reset();
        drive.shift(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        pid.reset();
        drive.shift(false);
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
    
    /* Hack -- use RMSE for onTarget() */
    private Queue<Double> errs = new ArrayDeque<Double>(MOV_AVG_COUNT + 1);
    private double sqerr = 0.0D;
    
    private synchronized double _getRMSE_hack() {
        return Math.sqrt(sqerr / errs.size());
    }
    
    private synchronized void _updateRMSE_hack() {
        double err = pid.getError();
        errs.add(err);
        sqerr += err * err;
        if(errs.size() > MOV_AVG_COUNT) {
            double staleerr = errs.remove();
            sqerr -= staleerr * staleerr;
        }
    }
}
