package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.interfaces.IGyro3D;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;

/**
 * Logical implementation of the IPositionTracker interface
 * @author Duncan
 */
public class PositionLogic implements IPositionTracker {
    
    private final IGyro3D gyro;
    private final IEncoder left;
    private final IEncoder right;
    private double lDist;
    private double rDist;
    private double x;
    private double y;
    private double heading;
    
    public PositionLogic(IGyro3D _gyro, IEncoder _left, IEncoder _right) {
        gyro = _gyro;
        left = _left;
        right = _right;
    }
    
    @Override
    public void disable() {/* Pure sensors are disable-safe */}
    
    @Override
    public void update() {
        heading = gyro.getYaw();
        double dl = left.getDistance() - lDist; 
        double dr = right.getDistance() - rDist;
        lDist += dl;
        rDist += dr;
        x += Math.sin(Math.toRadians(heading)) * (dl + dr) / 2D;
        y += Math.cos(Math.toRadians(heading)) * (dl + dr) / 2D;
    }
    
    @Override
    public double getX() {
        return x;
    }
    
    @Override
    public double getY() {
        return y;
    }
    
    @Override
    public double getHeading() {
        return heading;
    }
    
}
