package org.usfirst.frc.team1719.robot.mockHardware;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class MockPIDSource implements PIDSource {

	double rate;
	double displacement;
	
	PIDSourceType sourceType;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	@Override
	public double pidGet() {
		if (sourceType == PIDSourceType.kDisplacement) {
			return displacement;
		}
		else {
			return rate;
		}
	}
	
	public void setRate(double newRate) {
		this.rate = newRate;
	}
	
	public void setDisplacement(double newDisplacement) {
		this.displacement = newDisplacement;
	}	
	
}
