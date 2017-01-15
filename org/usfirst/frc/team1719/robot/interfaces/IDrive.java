package org.usfirst.frc.team1719.robot.interfaces;

import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.IGyro3D;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * Interface around drive for mockability
 * @author aaron
 *
 */
public interface IDrive extends GenericSubsystem{
    public void moveArcade(double spd, double dir);
    public void moveTank(double l, double r);
    public void shift(boolean fast);
    public void setMaxSpeed(double spd);
    public IEncoder getEncoderL();
    public IEncoder getEncoderR();
    public Accelerometer getAccelerometer();
    public IGyro3D getGyro();
}
