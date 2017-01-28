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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Move to an absolute position specified by (x,y)
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
        public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}

        @Override
        public double pidGet() {
            return posTracker.getHeading() - pathAngle - val;
        }
    }
    
    /*private class AuxPID {
        double p;
        double i;
        double d;
        double lasterr = 0;
        double sumerr = 0;
        AuxPID() {}
        void setPID(double _p, double _i, double _d) {
            p = _p; i = _i; d = _d;
        }
        double output(double cur_err) {
            double de = cur_err - lasterr;
            lasterr = cur_err;
            return p * cur_err + i * (sumerr += cur_err) + d * (de);
        }
    }*/
    
    private static final double SQ_TOLERANCE = 25.0D;
    private static final double SPD = 0.5D;
    private static final double MAX_ANGLE_TOLERANCE = 45.0D;
    private static final double MIN_ANGLE_TOLERANCE = 18.0D;
    
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
	/*private AuxPID auxPID0;
	private AuxPID auxPIDCtrl;*/

    private IDashboard dash;
    private boolean init = false;
    private boolean turning = false;
	
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
    	/*auxPID0 = new AuxPID();
    	auxPIDCtrl = new AuxPID();*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initX = posTracker.getX();
    	initY = posTracker.getY();
    	pathAngle = Math.toDegrees(Math.atan2(desiredX - initX, desiredY - initY));
    	desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 1), SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
    	        SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
    	rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 1), SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
    	        SmartDashboard.getNumber("MoveToPos K[1][D]", 0));
    	/*auxPIDCtrl.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 1), SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
    	        SmartDashboard.getNumber("MoveToPos K[1][D]", 0));*/
    	        
    	desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 0.1), SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
    	        SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
        rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.1), SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                SmartDashboard.getNumber("MoveToPos K[1][D]", 0));
    	 
      	desiredHeadingController.setSetpoint(0);
    	rotateController.setSetpoint(0);
    	rotateController.setInputRange(-180.0D,  180.0D);
    	rotateController.setContinuous();
    	rotateController.setOutputRange(-1.0D, 1.0D);
        rotateController.enable();
    	desiredHeadingController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(oi.getResetPIDConstants()) {
          desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 0.1), SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
            /*auxPIDCtrl.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 1), SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[1][D]", 0));*/
            rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.1), SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[1][D]", 0));
        }
        if(init) {
            initialize();
            init = false;
        }
        errX = desiredX - posTracker.getX();
        errY = desiredY - posTracker.getY();
        double atanXY = Math.toDegrees(Math.atan2(errX, errY));
        if(Math.abs(atanXY - posTracker.getHeading()) > MAX_ANGLE_TOLERANCE) {
            turning = true;
            desiredHeadingController.disable();
            pidhelper.pidWrite(0.0D);
        } else if(Math.abs(atanXY - posTracker.getHeading()) < MIN_ANGLE_TOLERANCE) {
            turning = false;
            desiredHeadingController.enable();
        }
        if(turning) {
           pathAngle = atanXY;System.out.println("Turning to heading " + pathAngle + "; power " + rotSpd + " ctrl " + rotateController.get() + "FROM" + rotateController.getError() + "K[P]=" + rotateController.getP() + "enabled=" + rotateController.isEnabled());
           drive.moveTank(rotSpd, -rotSpd);
        } else {
            double offPathAngle = Math.atan2(errX, errY) - Math.toRadians(pathAngle);
            distOffPath = Math.sin(offPathAngle) * Math.sqrt(errX * errX + errY * errY);System.out.println("Following path : power " + rotSpd + "Rotator " + rotateController.get());
            drive.moveArcade(SPD, rotSpd);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /* Finished if the Euclidean distance from target is less than tolerance or if the driver aborts */
        return ((errX * errX + errY * errY) < SQ_TOLERANCE) || oi.getAbortAutomove();
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
