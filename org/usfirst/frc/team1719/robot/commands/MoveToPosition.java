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
 * Move to an absolute position specified by (x,y)
 * @author Duncan
 */
public class MoveToPosition extends Command implements PIDSource, PIDOutput {

    /**
     * Class used to store a PID output and apply it as a setpoint for a PID input
     * @author Duncan
     */
    private class PIDHelper implements PIDSource, PIDOutput {
        private double val = 0;
        @Override
        public void pidWrite(double output) {
            val = output;
        }

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}

        @Override
        public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}

        @Override
        public double pidGet() {
            return posTracker.getHeading() - pathAngle - val;
        }
    }
  
    private static final double SQ_TOLERANCE = 8.0D;
    private static final double SPD = 0.5D;
    private static final double MAX_ANGLE_TOLERANCE = 45.0D;
    private static final double MIN_ANGLE_TOLERANCE = 18.0D;
    
	private double initX = 0;
	private double initY = 0;
	
	private IPositionTracker posTracker;
	private IDrive drive;
	private IRobot robot;
	private IOI oi;
	
	private double desiredX = 0; private final double parX;
	private double desiredY = 0; private final double parY;
	private double errX = 0;
	private double errY = 0;
	private double pathAngle = 0;
	private double distOffPath = 0;
	private double rotSpd = 0;
	private PIDHelper pidhelper;
	private PIDController desiredHeadingController;
	private PIDController rotateController;
	private PIDController rotateControllerStill;
    private boolean init = false;
    private boolean turning = false;
    private final boolean absolute;
    private final boolean doHardTurns;
	
    public MoveToPosition(double _desiredX, double _desiredY, IPositionTracker _posTracker,
            IDrive _drive, IRobot _robot, boolean _absolute, boolean _doHardTurns) {
        absolute = _absolute;
    	parX = desiredX = _desiredX;
    	parY = desiredY = _desiredY;
    	doHardTurns = _doHardTurns;
    	posTracker = _posTracker;
    	robot = _robot;
    	drive = _drive;
    	oi = robot.getOI();
    	try {
    		requires( (Subsystem) drive);
    	}
    	catch (ClassCastException e) {
    		System.out.println("Running Unit test on MoveToPosition");
    	}
    	pidhelper = new PIDHelper();
    	desiredHeadingController = new PIDController(0, 0, 0, this, pidhelper);
    	rotateController = new PIDController(0, 0, 0, pidhelper, this);
    	rotateControllerStill = new PIDController(0, 0, 0, pidhelper, this);
    }

    public MoveToPosition(double _desiredX, double _desiredY, IPositionTracker _posTracker, IDrive _drive,
            IRobot _robot, boolean _absolute) {
        this(_desiredX, _desiredY, _posTracker, _drive, _robot, _absolute, true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initX = posTracker.getX();
    	initY = posTracker.getY();
    	if(!absolute) {
    	    desiredX = parX + initX;
    	    desiredY = parY + initY;
    	}
    	pathAngle = Math.toDegrees(Math.atan2(desiredX - initX, desiredY - initY));
    	desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 5.0),
    	        SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
    	        SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
        rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04),
                SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1));
        rotateControllerStill.setPID(SmartDashboard.getNumber("TurnToHeading K[P]", 0.02),
                SmartDashboard.getNumber("TurnToHeading K[I]", 0.006),
                SmartDashboard.getNumber("TurnToHeading K[D]", 0.1));
      	desiredHeadingController.setSetpoint(0);
      	desiredHeadingController.setOutputRange(-90.0D, 90.0D);
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D,  180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
    	rotateControllerStill.setSetpoint(0);
    	rotateControllerStill.setInputRange(-180.0D,  180.0D);
    	rotateControllerStill.setContinuous();
    	rotateControllerStill.setOutputRange(-1.0D, 1.0D);
    	desiredHeadingController.enable();
    	rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(true){//(oi.getResetPIDConstants()) {
            desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 5.0),
                    SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
            rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04),
                    SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1));
            rotateControllerStill.setPID(SmartDashboard.getNumber("TurnToHeading K[P]", 0.02),
                    SmartDashboard.getNumber("TurnToHeading K[I]", 0.006),
                    SmartDashboard.getNumber("TurnToHeading K[D]", 0.1));
        }
        if(init) {
            initialize();
            init = false;
        }
        errX = desiredX - posTracker.getX();
        errY = desiredY - posTracker.getY();
        double atanXY = Math.toDegrees(Math.atan2(errX, errY));
        /* Hack -- check the angular heading compared to where the target is */
        if(doHardTurns) {
            double head_m_atxy = (atanXY - posTracker.getHeading()) % 360.0D;
            if(head_m_atxy > 180.0D) {
                head_m_atxy -= 360.0D;
            }
            if(head_m_atxy <  -180.0D) {
                head_m_atxy += 360.0D;
            }
            if(Math.abs(head_m_atxy) > MAX_ANGLE_TOLERANCE) { /* Are we so far off target that the 1094 algorithm won't work well? */
                turning = true;
                desiredHeadingController.disable();
                rotateController.disable();
                rotateControllerStill.enable();
                pidhelper.pidWrite(0.0D);
            } else if(Math.abs(head_m_atxy) < MIN_ANGLE_TOLERANCE) { /* Have we gotten to almost directly the direction we wish to go? */
                turning = false;
                desiredHeadingController.enable();
                rotateController.enable();
                rotateControllerStill.disable();
            }
        }
        if(doHardTurns && turning) { /* Just use one PID loop to turn in place*/
            drive.shift(true);
           pathAngle = atanXY;
           rotSpd = rotateControllerStill.get();
           System.out.println("Turning to heading " + pathAngle + "; power " + rotSpd + " ctrl " + rotateControllerStill.get() + "FROM" + rotateControllerStill.getError() + "K[P]=" + rotateControllerStill.getP() + "enabled=" + rotateControllerStill.isEnabled());
           drive.moveTank(rotSpd, -rotSpd);
        } else { /* Use 1094 algorithm */
            drive.shift(false);
            double offPathAngle = Math.atan2(errX, errY) - Math.toRadians(pathAngle);
            distOffPath = Math.sin(offPathAngle) * Math.sqrt(errX * errX + errY * errY);
            System.out.println("Following path : power " + rotSpd + "Rotator " + rotateController.get());
            drive.moveArcade(SPD, rotSpd);
        }
        SmartDashboard.putNumber("MTP e\u27c2", distOffPath);
        SmartDashboard.putNumber("MTP Desired angle", pidhelper.val);
        SmartDashboard.putNumber("MTP current angle", posTracker.getHeading() - pathAngle);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /* Finished if the Euclidean distance from target is less than tolerance or if the driver aborts */
        return oi.getAbortAutomove() || (doHardTurns ?
                ((errX * errX + errY * errY) < SQ_TOLERANCE) :
                    (Math.abs(Math.atan2(errX, errY) - Math.toRadians(pathAngle)) > 90)) ;
    }

    // Called once after isFinished returns true
    protected void end() {
        init = true;
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
        return distOffPath;
    }
    
}
