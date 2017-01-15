package org.usfirst.frc.team1719.robot.controllers;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class NoiseCorrectingPIDController extends PIDController{

	public NoiseCorrectingPIDController(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output) {
		super(Kp, Ki, Kd, source, output);
		// TODO Auto-generated constructor stub
	}


}
