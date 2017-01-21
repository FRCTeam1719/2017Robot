package org.usfirst.frc.team1719.robot.interfaces;

public interface IGearHandler extends GenericSubsystem{

	public double getDistance(double lidar, double maxDistance);
	public boolean isGearHeld();
	public void setClamp(boolean clamp);
	public void setHight(boolean height);
}
