package org.usfirst.frc.team1719.robot.actuators;

import org.usfirst.frc.team1719.robot.interfaces.IServo;

/**
 * Simple wrapper to allow for a mockable servo
 * @author aaron
 *
 */
public class Servo extends edu.wpi.first.wpilibj.Servo implements IServo{

	public Servo(int channel) {
		super(channel);
	}

}
