package org.usfirst.frc.team1719.robot.commands;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team1719.robot.RobotMap;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Default command used for controlling the drive
 * 
 * @author aaron
 *
 */
public class UseDrive extends Command {

	public static final String LEFT_DRIVE_KP = "Left Drive kP: ";
	public static final String LEFT_DRIVE_KF = "Left Drive kF: ";
	public static final String LEFT_DRIVE_KD = "Left Drive kD: ";

	public static final String RIGHT_DRIVE_KP = "Right Drive kP: ";
	public static final String RIGHT_DRIVE_KF = "Right Drive kF: ";
	public static final String RIGHT_DRIVE_KD = "Right Drive kD: ";

	PIDController leftController;
	PIDController rightController;
	private final IRobot robot;
	private final IOI oi;
	private final IDrive drive;

	private double highMaxSpd = 250D;
	private double lowMaxSpd = 50D;
	private double maxSpeed = highMaxSpd;
	private double MAX_SPEED_SCALING_FACTOR = 1.2;
	double SYNCH_TOLERANCE = 0.15;

	private final double JOYSTICK_DEADZONE = 0.05;

	double left_kP = 0;
	double left_kF = 1 / maxSpeed;
	double left_kD = 0;

	double right_kP = 0;
	double right_kF = 1 / maxSpeed;
	double right_kD = 0;

	volatile double leftMotorOutput = 0;
	volatile double rightMotorOutput = 0;

	private final int BUFFER_SIZE = 10;
	private List<Double> ampBuffer = new LinkedList<>();
	private PowerDistributionPanel pdp = RobotMap.pdp;

	// PID objects
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
	 * @param _drive
	 *            Drive interface to control
	 * @param _robot
	 *            Robot to grab external data from
	 */
	public UseDrive(IDrive _drive, IRobot _robot) {
		drive = _drive;
		robot = _robot;
		oi = robot.getOI();

		drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
		drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
		try {
			requires((Subsystem) drive);
		} catch (ClassCastException e) {
			System.out.println("Running unit test on UseDrive command");
		}

		leftController = new PIDController(left_kP, 0, left_kD, left_kF, drive.getEncoderL(), new leftDrivePIDOut());
		rightController = new PIDController(right_kP, 0, right_kD, left_kF, drive.getEncoderR(),
				new rightDrivePIDOutput());

		SmartDashboard.putData("Left Drive PID Controller", leftController);
		SmartDashboard.putData("Left Drive rate", (Encoder) drive.getEncoderL());

		SmartDashboard.putData("Right Drive PID Controller", rightController);
		SmartDashboard.putData("Right Drive rate", (Encoder) drive.getEncoderR());
	}

	private void updateBuffer(double newReading) {
		if (ampBuffer.size() == BUFFER_SIZE) {
			ampBuffer.remove(0);
		}
		ampBuffer.add(newReading);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		drive.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
		drive.getEncoderR().setPIDSourceType(PIDSourceType.kRate);

		leftController.setOutputRange(-1, 1);
		rightController.setOutputRange(-1, 1);

		double maxInput = maxSpeed * MAX_SPEED_SCALING_FACTOR;
		leftController.setInputRange(-(maxInput), maxInput);
		rightController.setInputRange(-(maxInput), maxInput);

		leftController.setContinuous(false);
		rightController.setContinuous(false);

		leftController.setToleranceBuffer(20);
		rightController.setToleranceBuffer(20);

		leftController.setPercentTolerance(5);
		rightController.setPercentTolerance(5);

		leftController.enable();
		rightController.enable();
		// Setup dashboard constants
		SmartDashboard.putNumber(UseDrive.LEFT_DRIVE_KP, 0.01);
		SmartDashboard.putNumber(UseDrive.LEFT_DRIVE_KD, 0);

		SmartDashboard.putNumber(UseDrive.RIGHT_DRIVE_KP, 0.01);
		SmartDashboard.putNumber(UseDrive.RIGHT_DRIVE_KD, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		boolean shifted = drive.isShifted();
		setPIDConstantsFromDashboard();

		double leftJoystick = (-oi.getLeftY()) * Math.abs(oi.getLeftY());
		double rightJoystick = (-oi.getRightY()) * Math.abs(oi.getRightY());

		if (Math.abs(leftJoystick - rightJoystick) < SYNCH_TOLERANCE) {
			double avg = (leftJoystick + rightJoystick) / 2;
			leftJoystick = avg;
			rightJoystick = avg;
		}

		double desiredLeftRate = leftJoystick * maxSpeed;
		double desiredRightRate = rightJoystick * maxSpeed;

		if (Math.abs(leftJoystick) < JOYSTICK_DEADZONE) {
			leftMotorOutput = 0;
			leftController.setSetpoint(0);
			leftController.reset();
			desiredLeftRate = 0;

		} else {
			leftController.enable();
			leftController.setSetpoint(desiredLeftRate);
		}

		if (Math.abs(rightJoystick) < JOYSTICK_DEADZONE) {
			rightMotorOutput = 0;
			rightController.setSetpoint(0);
			rightController.reset();
			desiredRightRate = 0;
		} else {
			rightController.enable();
			rightController.setSetpoint(desiredRightRate);
		}

		drive.moveTank(leftMotorOutput, rightMotorOutput);

		if (shifted) {
			maxSpeed = lowMaxSpd;
		} else {
			maxSpeed = highMaxSpd;
		}
		leftController.setInputRange(-(maxSpeed * MAX_SPEED_SCALING_FACTOR), maxSpeed * MAX_SPEED_SCALING_FACTOR);
		leftController.setPID(leftController.getP(), leftController.getI(), leftController.getD(), (1 / maxSpeed));

		rightController.setInputRange(-(maxSpeed * MAX_SPEED_SCALING_FACTOR), maxSpeed * MAX_SPEED_SCALING_FACTOR);
		rightController.setPID(rightController.getP(), rightController.getI(), rightController.getD(), (1 / maxSpeed));

		// Check manual override
		if (oi.getShifter()) {
			drive.shift(IDrive.Shift.LOW);
		} else {
			// Average the readings
			double avgReading = 0;
			for (int i = 0; i < 3; i++) {
				avgReading += pdp.getCurrent(i);
			}
			avgReading /= 4;
			updateBuffer(avgReading);
			// Average the buffer
			double avgBuffer = 0;
			for (double reading : ampBuffer) {
				avgBuffer += reading;
			}
			avgBuffer /= ampBuffer.size();

			if (avgBuffer > 120) {
				drive.shift(IDrive.Shift.LOW);
			} else if (avgBuffer < 100) {
				drive.shift(IDrive.Shift.HIGH);
			}
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
		left_kP = SmartDashboard.getNumber(LEFT_DRIVE_KP, 0);
		left_kD = SmartDashboard.getNumber(LEFT_DRIVE_KD, 0);

		right_kP = SmartDashboard.getNumber(RIGHT_DRIVE_KP, 0);
		right_kD = SmartDashboard.getNumber(RIGHT_DRIVE_KD, 0);

		leftController.setPID(left_kP, 0, left_kD);
		rightController.setPID(right_kP, 0, right_kD);
	}
}
