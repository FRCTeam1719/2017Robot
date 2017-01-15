package org.usfirst.frc.team1719.robot.sensors;

import java.util.ArrayDeque;
import java.util.Queue;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NoiseCorrectingNavX extends NAVX {

	Queue<Double> velocityReadings;
	int maxVelocityReadings = 5;
	
	public NoiseCorrectingNavX(Port port) {
		super(port);
		velocityReadings = new ArrayDeque<Double>(2);
	}
	
	
	@Override
	public double pidGet() {
		if (getPIDSourceType() == PIDSourceType.kDisplacement) {
			return getYaw();
		}
		else {
			velocityReadings.add((double) getVelocityX());
			if (velocityReadings.size() > maxVelocityReadings) {
				velocityReadings.remove();
			}
			
			return getAvgVelocity();
		}
	}
	double getAvgVelocity() {
		double sum = 0;
		for (Double v : velocityReadings) {
			sum += v;
		}
		return sum / velocityReadings.size();
	}
}
