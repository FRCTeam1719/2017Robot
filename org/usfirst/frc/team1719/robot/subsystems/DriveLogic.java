package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.actuators.ISolenoid;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.IGyro3D;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * Drive Subsystem class
 * @author aaron
 *
 */
public class DriveLogic implements IDrive {
    
    private SpeedController left;
    private SpeedController right;
    private ISolenoid shifter;
    private IEncoder lEncoder;
    private IEncoder rEncoder;
    private Accelerometer accelerometer;
    private IGyro3D gyro;
    private double maxSpd = 1.0D;
    
    /**
     * 
     * @param l Left Speed Controller
     * @param r Right Speed Controller
     * @param shift Solenoid to control shifter
     * @param lEnc Left encoder
     * @param rEnc Right encoder
     * @param acc Accelerometer 
     * @param gyr Gyroscope
     * @param wheelSize Circumference of wheel (ft) to config encoders
     */
    public DriveLogic(SpeedController l, SpeedController r, ISolenoid shift, IEncoder lEnc,
            IEncoder rEnc, Accelerometer acc, IGyro3D gyr, double wheelSize) {
    	this(l,r,shift,lEnc,rEnc,acc,gyr);
    	lEnc.config(wheelSize);
    	rEnc.config(wheelSize);
    }

    /**
     * 
     * @param l Left Speed Controller
     * @param r Right Speed Controller
     * @param shift Solenoid to control shifter
     * @param lEnc Left encoder
     * @param rEnc Right encoder
     * @param acc Accelerometer 
     * @param gyr Gyroscope
     */
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
    
    
    /**
     *Implements an arcade drive
     *Typical setup is one joystick for speed, another for direction
     *@param speed speed to drive, from -1 - 1
     *@param direction angle to drive in
     */
    @Override
    public void moveArcade(double speed, double direction) {
        speed = clip(speed);
        direction = clip(direction);
        /* Copied from edu.wpi.first.wpilib.RobotDrive */
        if (speed > 0.0) {
            if (direction > 0.0) {
                left.set(speed - direction);
                right.set(Math.max(speed,direction));
            } else {
                left.set(Math.max(speed, -direction));
                right.set(speed + direction);
            }
        } else {
            if (direction > 0.0) {
                left.set(-Math.max(-speed, direction));
                right.set(speed + direction);
            } else {
                left.set(speed - direction);
                right.set(-Math.max(-speed, -direction));
            }
        }
    }
   
    /**
     * Implements a tank drive, speed is capped by {@link #setMaxSpeed(double)}
     * @param left power on  a -1 - 1 scale  
     * @param right power on a -1 - 1 scale
     */
    @Override
    public void moveTank(double left, double right) {
        this.left.set(left);
        this.right.set(right);
    }
    
    /**
     * Engages or disengages the shifter
     */
    @Override
    public void shift(boolean fast) {
        shifter.set(fast);
    }
    
    /**
     * Set the max speed to clip the tank drive to
     */
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

	@Override
	public void disable() {
		shifter.set(false);
		
	}
}
