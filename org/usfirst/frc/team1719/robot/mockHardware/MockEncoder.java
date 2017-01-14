package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.sensors.IEncoder;

import edu.wpi.first.wpilibj.PIDSourceType;

public class MockEncoder implements IEncoder {
	
	double rate = 0;
	double dist = 0;
	
	PIDSourceType pidSourcetype = PIDSourceType.kDisplacement;
	
	double distancePerPulse = 1;
	boolean direction = true;
	@Override
	public void reset() {
		dist = 0;
	}

	@Override
	public boolean getStopped() {
		return false;
	}

	@Override
	public boolean getDirection() {
		return direction;
	}

	public void setDistance(double newDist) {
		this.dist = newDist;
	}
	
	@Override
	public double getDistance() {
		return dist;
	}

	public void setRate(double newRate) {
		this.rate = newRate;
	}
	
	@Override
	public double getRate() {
		return rate;
	}

	public double getDistancePerPulse() {
		return this.distancePerPulse;
	}
	
	@Override
	public void setDistancePerPulse(double distancePerPulse) {
		this.distancePerPulse = distancePerPulse;
	}

	@Override
	public void setReverseDirection(boolean reverseDirection) {
		this.direction = reverseDirection;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourcetype = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourcetype;
	}

	@Override
	public double pidGet() {
		if (pidSourcetype == PIDSourceType.kDisplacement) {
			return getDistance();
		}
		else {
			return getRate();
		}
	}

}
