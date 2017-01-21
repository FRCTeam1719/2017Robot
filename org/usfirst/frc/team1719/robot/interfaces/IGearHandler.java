package org.usfirst.frc.team1719.robot.interfaces;

public interface IGearHandler extends GenericSubsystem{

public void getDistance(double lidar, double lidarMultiplyer);
public void isGearHeld(boolean holding);
public void setClamp(boolean clamp);
public void setHight(boolean height);

}
