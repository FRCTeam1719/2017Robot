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
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void moveTank(double l, double r) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void shift(boolean fast) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void setMaxSpeed(double spd) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public IEncoder getEncoderL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public IEncoder getEncoderR() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Accelerometer getAccelerometer() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public IGyro3D getGyro() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
