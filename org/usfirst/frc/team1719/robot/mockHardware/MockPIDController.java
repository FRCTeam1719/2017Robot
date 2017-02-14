package org.usfirst.frc.team1719.robot.mockHardware;

import java.util.ArrayDeque;
import java.util.Queue;

import org.usfirst.frc.team1719.robot.interfaces.IPIDController;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class MockPIDController implements IPIDController {

	boolean enabled = false;
	double kP = 0;
	double kI = 0;
	double kD = 0;
	
	Queue<Double> errorBuffer;
	int bufferLength = 1;
	double bufferTotal = 0.0;
	PIDSource source;
	PIDOutput output;
	
	double setPoint;
	
	public MockPIDController(double p, double i, double d, PIDSource input, PIDOutput output) {
		kP = p;
		kI = i;
		kD = d;
		
		this.source = input;
		this.output = output;
		
		errorBuffer = new ArrayDeque<Double>(bufferLength + 1);
	}
	
	@Override
	public void setPID(double p, double i, double d) {
		kP = p;
		kI = i;
		kD = d;
	}

	@Override
	public double getP() {
		return kP;
	}

	@Override
	public double getI() {
		return kI;
	}

	@Override
	public double getD() {
		return kD;
	}

	@Override
	public void setSetpoint(double setpoint) {
		this.setPoint = setpoint;
	}

	@Override
	public double getSetpoint() {
		return setPoint;
	}

	@Override
	public double getError() {
		return setPoint - source.pidGet();
	}

	@Override
	public void enable() {
		this.enabled = true;
	}

	@Override
	public void disable() {
		this.enabled = false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		if (!enabled) {
			return;
		}
		double currError = source.pidGet();
		double output = 0;
		
		errorBuffer.add(currError);
		bufferTotal += currError;
		
		if (errorBuffer.size() > bufferLength) {
			bufferTotal -= errorBuffer.remove();
		}
		
		this.output.pidWrite(output);
	}

}
