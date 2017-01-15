package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDashboard;
import org.usfirst.frc.team1719.robot.interfaces.IPixyCam;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TrackTargets extends Command implements PIDOutput {

    IRobot robot;
    IPixyMount mount;
    IPixyCam cam;
    IDashboard dash;
    private double pidout = 0.0D;
    private double xPos;
    private PIDController pid;
    private static final double PID_MAX = 1.0D;
    private static double ALPHA;
    
    public TrackTargets(IRobot _robot, IPixyMount _mount, IPixyCam _cam) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        robot = _robot;
        mount = _mount;
        cam = _cam;
        dash = robot.getDashboard();
        try {
            requires((Subsystem) mount);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on TrackTargets command");
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        xPos = mount.getX();
        pid = new PIDController(dash.getNumber("Servo K[P]"),dash.getNumber("Servo K[I]"),
                dash.getNumber("Servo K[D]"), cam, this);
        pid.setInputRange(-PID_MAX, PID_MAX);
        pid.setOutputRange(-PID_MAX, PID_MAX);
        pid.setSetpoint(0);
        pid.enable();
        ALPHA = dash.getNumber("Servo \u03b1");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        xPos += ALPHA * pidout;
        mount.setX(xPos);
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

    @Override
    public void pidWrite(double output) {
        pidout = output;
    }
}
