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
 * Auxilliary class for tuning the MoveToPosition command 
 * @author Duncan
 */
public class MTPTunerL0 extends Command implements PIDSource, PIDOutput {

    /**
     * Class used to store a PID output and apply it as a setpoint for a PID input
     * @author Duncan
     */
    private class PIDHelper implements PIDSource, PIDOutput {
        private double val = 0;
        @Override
        public void pidWrite(double output) {
            val = output % 360;
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
   
    public MTPTunerL0(double _desiredX, double _desiredY, IPositionTracker _posTracker,
            IDrive _drive, IRobot _robot) {
        desiredX = _desiredX;
        desiredY = _desiredY;
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
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pathAngle = Math.toDegrees(Math.atan2(desiredX, desiredY));
        desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 0.1),
                SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
                SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
        rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04),
                SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1));
        desiredHeadingController.setSetpoint(0);
        desiredHeadingController.setOutputRange(Double.MIN_VALUE, Double.MAX_VALUE);
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D,  180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
        desiredHeadingController.enable();
        rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(true) {//oi.getResetPIDConstants()) {
            double kp = SmartDashboard.getNumber("MoveToPos K[0][P]", 0.1);
            desiredHeadingController.setPID(kp,
                    SmartDashboard.getNumber("MoveToPos K[0][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
            System.out.println("KP: " + kp);
            rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04),
                    SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                    SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1));
            System.out.println("Updating PID");
        }
        System.out.println("Actual KP" + desiredHeadingController.getP());
        errX = desiredX - posTracker.getX();
        errY = desiredY - posTracker.getY();
        double offPathAngle = Math.atan2(errX, errY) - Math.toRadians(pathAngle);
        distOffPath = Math.sin(offPathAngle) * Math.sqrt(errX * errX + errY * errY);
        //System.out.println("Following path : power " + rotSpd + "Rotator " + rotateController.get());
        drive.moveArcade(SPD, -rotSpd);
        double displayNoiseHack = Math.random() * 0.01D;
        SmartDashboard.putNumber("MTP Desired angle", pidhelper.val + displayNoiseHack);
        SmartDashboard.putNumber("MTP e\u27c2", distOffPath + displayNoiseHack);
        SmartDashboard.putNumber("MTP current angle", posTracker.getHeading() - pathAngle + displayNoiseHack);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        /* Finished if the Euclidean distance from target is less than tolerance or if the driver aborts */
        return oi.getAbortAutomove() || ((errX * errX + errY * errY) < SQ_TOLERANCE);
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
        return distOffPath;
    }
    
}
