package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.actuators.ISolenoid;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.sensors.IDigitalInput;

public class GearHandlerLogic implements IGearHandler{

	private ISolenoid shiftHeight;
	private ISolenoid shiftClamp;
	private IDigitalInput limit;
	public double distance;


	@Override
	public double getDistance(double lidar, double maxDistance) {
		// TODO Auto-generated method stub
		//Distance = (MaxDistance / Range) * (Lidar output - Low mA limit)*scaling multiplier
		distance = (maxDistance / 20)*((lidar-4)*3.125);
		return distance;
	}

	public boolean isGearHeld() {
		return !limit.isPressed;
		// TODO Auto-generated method stub
		}

	@Override
	public void setClamp(boolean clamp) {
		// TODO Auto-generated method stub
		shiftClamp.set(clamp);
	}

	@Override
	public void setHight(boolean height) {
		// TODO Auto-generated method stub
		shiftHeight.set(false);
	}
	@Override
	public void disable() {
		// TODO Auto-generated method stub
		shiftClamp.set(false);
		shiftHeight.set(false);
	}
	
}
