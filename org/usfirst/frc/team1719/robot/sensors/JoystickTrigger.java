package org.usfirst.frc.team1719.robot.sensors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Simple class that provides easy triggering of command via the analog trigger on a joystick
 * @author aaron
 *
 */
public class JoystickTrigger extends Trigger{

	private final int axis;
	private final Joystick joystick;
	
	public JoystickTrigger(Joystick joystick,int axis){
		this.joystick = joystick;
		this.axis = axis;
	}

	@Override
	public boolean get() {
		return joystick.getRawAxis(axis) > 0.55;
	}

}
