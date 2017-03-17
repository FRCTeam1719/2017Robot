package org.usfirst.frc.team1719.robot.commands;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team1719.robot.RobotMap;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UseDriveNoPID extends Command {

	IOI oi;
	IDrive drive;
	private boolean shifted = false;
	// TODO CHANGE THESE PORTS
	private int[] pdpPorts = { 1, 2, 3, 4 };
	private List<Double> ampReadings[];
	private Timer shiftTimer = new Timer();

	public UseDriveNoPID(IOI oi, IDrive drive) {
		this.oi = oi;
		this.drive = drive;

		try {
			requires((Subsystem) drive);
		} catch (ClassCastException e) {
			System.out.println("UNIT test on useDriveNoPID");
		}
		ampReadings = new List[4];
		// Create a list for every pdpPort
		for (int i = 0; i < ampReadings.length; i++) {
			ampReadings[i] = new LinkedList<Double>();
		}
	}

	public void initialize() {
		drive.shift(shifted);
	}

	public void execute() {
		System.out.println("Left: " + drive.getEncoderL().getDistance());
		System.out.println("Right: " + drive.getEncoderR().getDistance());
		double right = oi.getLeftY() * Math.pow(Math.abs(oi.getLeftY()), 2);
		double left = oi.getRightY() * Math.pow(Math.abs(oi.getRightY()), 2);

		if (shifted != shouldShift()) {
			drive.shift(shifted = !shifted);
		}
		drive.moveTank(left, right);
	}

	boolean speedShift = false;
	boolean ampShift = false;
	boolean impactShift = false;
	private double last_world_linear_accel_x;
	private double last_world_linear_accel_y;
	private double kCollisionThreshold_DeltaG = 0.5f;

	private boolean shouldShift() {

		// TODO FIX THE CONTANTS HERE; THEY ARE COMPLETELY MADE UP
		double speed = Math.abs(RobotMap.navx.getVelocityY());
		boolean mostlySameDirection = Math.abs(oi.getLeftY()) > 0 && Math.abs(oi.getRightY()) > 0;
		boolean shouldMoveFast = Math.abs(oi.getLeftY()) > 0.5 && Math.abs(oi.getRightY()) > 0.5;
		// Does our speed suggest we're pushing against someone?
		if (mostlySameDirection && shouldMoveFast) {
			// Upper limit
			if (speed < 0.25) {
				speedShift = true;
			} else if (speed > 0.50) {
				speedShift = false;
			}
		}

		// Does amperage suggest we should shift?
		// TODO MAKE SURE THESE ARE THE RIGHT PDP PORTS

		// Update our lists & then average
		boolean trippedUpperLimit = false;
		boolean trippedLowerLimit = false;
		for (int i = 0; i < pdpPorts.length; i++) {
			ampReadings[i].add(RobotMap.pdp.getCurrent(pdpPorts[i]));
			if (ampReadings[i].size() > 10) {
				ampReadings[i].remove(0);
			}
			// average
			double sum = 0;
			for (double reading : ampReadings[i]) {
				sum += reading;
			}
			sum /= ampReadings[i].size();
			// Upper limit
			if (sum > 100) {
				trippedUpperLimit = true;
			} else if (sum < 20) {
				// Lower limit
				trippedLowerLimit = true;
			}
		}
		if (trippedUpperLimit) {
			ampShift = true;
		} else if (trippedLowerLimit) {
			ampShift = false;
		}

		// Impact shift?
		boolean collisionDetected = false;

		double curr_world_linear_accel_x = RobotMap.navx.getWorldLinearAccelX();
		double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
		last_world_linear_accel_x = curr_world_linear_accel_x;
		double curr_world_linear_accel_y = RobotMap.navx.getWorldLinearAccelY();
		double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
		last_world_linear_accel_y = curr_world_linear_accel_y;

		if ((Math.abs(currentJerkX) > kCollisionThreshold_DeltaG)
				|| (Math.abs(currentJerkY) > kCollisionThreshold_DeltaG)) {
			collisionDetected = true;
		}
		
		boolean shouldShift = false;
		if(collisionDetected || ampShift || speedShift){
			shouldShift = true;
			shiftTimer.reset();
			shiftTimer.start();
		}else if(shiftTimer.get() < 1){
			shouldShift = true;
		}else{
			shiftTimer.stop();
			if(oi.getShifter() != shifted){
				shouldShift = true;
			}
		}
		
		return shouldShift;

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
