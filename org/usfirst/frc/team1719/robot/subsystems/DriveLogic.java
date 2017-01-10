package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.actuators.ISolenoid;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.IGyro3D;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class DriveLogic implements IDrive {
    
    private SpeedController left;
    private SpeedController right;
    private ISolenoid shifter;
    private IEncoder lEncoder;
    private IEncoder rEncoder;
    private Accelerometer accelerometer;
    private IGyro3D gyro;
    private double maxSpd = 1.0D;
    
    public DriveLogic(SpeedController l, SpeedController r, ISolenoid shift, IEncoder lEnc,
            IEncoder rEnc, Accelerometer acc, IGyro3D gyr) {
        left = l;
        right = r;
        shifter = shift;
        lEncoder = lEnc;
        rEncoder = rEnc;
        accelerometer = acc;
        gyro = gyr;
    }
    
    @Override
    public void moveArcade(double spd, double dir) {
        spd = clip(spd);
        dir = clip(dir);
        /* Copied from edu.wpi.first.wpilib.RobotDrive */
        if (spd > 0.0) {
            if (dir > 0.0) {
                left.set(spd - dir);
                right.set(Math.max(spd,dir));
            } else {
                left.set(Math.max(spd, -dir));
                right.set(spd + dir);
            }
        } else {
            if (dir > 0.0) {
                left.set(-Math.max(-spd, dir));
                right.set(spd + dir);
            } else {
                left.set(spd - dir);
                right.set(-Math.max(-spd, -dir));
            }
        }
    }
    
    @Override
    public void moveTank(double l, double r) {
    	System.out.println("Left: " + l + "Right: " + r);
        left.set(clip(l));
        right.set(clip(r));
    }
    
    @Override
    public void shift(boolean fast) {
        shifter.set(fast);
    }
    
    @Override
    public void setMaxSpeed(double spd) {
        maxSpd = spd;
    }
    
    @Override
    public IEncoder getEncoderL() {
        return lEncoder;
    }
    
    @Override
    public IEncoder getEncoderR() {
        return rEncoder;
    }
    
    @Override
    public Accelerometer getAccelerometer() {
        return accelerometer;
    }
    
    @Override
    public IGyro3D getGyro() {
        return gyro;
    }
    
    private double clip(double d) {
        return Math.min(maxSpd, Math.max(-maxSpd, d));
    }
}
