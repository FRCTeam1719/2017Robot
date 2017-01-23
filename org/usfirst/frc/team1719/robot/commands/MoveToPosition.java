package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
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

/**
 *
 */
public class MoveToPosition extends Command implements PIDSource, PIDOutput {

    private class PIDHelper implements PIDSource, PIDOutput {
        private double val = 0;
        @Override
        public void pidWrite(double output) {
            val = output;
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}

        @Override
        public PIDSourceType getPIDSourceType() {return null;}

        @Override
        public double pidGet() {
            return distOffPath - val;
        }
    }
    
    private static final double SQ_TOLERANCE = 25.0D;
    private static final double SPD = 0.5D;
    
	private double initX = 0;
	private double initY = 0;
	
	private IPositionTracker posTracker;
	private IDrive drive;
	private IRobot robot;
	private IOI oi;
	
	private double desiredX = 0;
	private double desiredY = 0;
	private double errX = 0;
	private double errY = 0;
	private double pathAngle = 0;
	private double distOffPath = 0;
	private double rotSpd = 0;
	private PIDHelper pidhelper;
	
	private PIDController desiredHeadingController;
	private PIDController rotateController;

    private IDashboard dash;
	
    public MoveToPosition(double _desiredX, double _desiredY, IPositionTracker _posTracker,
            IDrive _drive, IRobot _robot) {
        
    	desiredX = _desiredX;
    	desiredY = _desiredY;
    	
    	posTracker = _posTracker;
    	robot = _robot;
    	drive = _drive;
    	oi = robot.getOI();
    	dash = robot.getDashboard();
    	try {
    		requires( (Subsystem) drive);
    	}
    	catch (ClassCastException e) {
    		System.out.println("Running Unit test on MoveToPosition");
    	}
    	pidhelper = new PIDHelper();
    	desiredHeadingController = new PIDController(0, 0, 0, this, pidhelper);
    	rotateController = new PIDController(0, 0, 0, pidhelper, this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initX = posTracker.getX();
    	initY = posTracker.getY();
    	pathAngle = Math.atan2(desiredY - initY, desiredX - initX);
    	
    	desiredHeadingController.setPID(dash.getNumber("MoveToPos K[0][P]"), dash.getNumber("MoveToPos K[0][I]"),
    	        dash.getNumber("MoveToPos K[0][D]"));
    	rotateController.setPID(dash.getNumber("MoveToPos K[1][P]"), dash.getNumber("MoveToPos K[1][I]"),
    	        dash.getNumber("MoveToPos K[1][D]"));
    	desiredHeadingController.setSetpoint(0);
    	rotateController.setSetpoint(0);
    	desiredHeadingController.enable();
    	rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        errX = desiredX - posTracker.getX();
        errY = desiredY - posTracker.getY();
        double offPathAngle = Math.atan2(errY, errX) - pathAngle;
        distOffPath = Math.sin(offPathAngle) * Math.sqrt(errX * errX + errY * errY);
        drive.moveArcade(SPD, rotSpd);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /* Finished if the Euclidean distance from target is less than tolerance or if the driver aborts */
        return ((errX * errX + errY * errY) < SQ_TOLERANCE) || oi.getAbortAutomove();
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
    public PIDSourceType getPIDSourceType() {return null;}

    @Override
    public double pidGet() {
        return distOffPath;
    }
}
