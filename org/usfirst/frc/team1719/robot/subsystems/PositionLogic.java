package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.interfaces.IGyro3D;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.sensors.NAVX;

/**
 * Logical implementation of the IPositionTracker interface
 * @author Duncan
 */
public class PositionLogic implements IPositionTracker {
    
    private final IGyro3D gyro;
    private final NAVX navX;
    private final IEncoder left;
    private final IEncoder right;
    private final double collisionThreshold = 0.5f;
    private boolean isTrustworthy;
    private double lastAccelX;
    private double lastAccelY;
    private double lDist;
    private double rDist;
    private double x;
    private double y;
    private double heading;
    
    public PositionLogic(NAVX navX, IEncoder _left, IEncoder _right) {
    	this.navX = navX;
        gyro = (IGyro3D) navX;
        left = _left;
        right = _right;
        isTrustworthy = true;
    }
    
    @Override
    public void disable() {/* Pure sensors are disable-safe */}
    
    @Override
    public void update() {
    	isTrustworthy = checkForCollision();
        heading = gyro.getYaw();
        double dl = left.getDistance() - lDist; 
        double dr = right.getDistance() - rDist;
        lDist += dl;
        rDist += dr;
        x += Math.sin(Math.toRadians(heading)) * (dl + dr) / 2D;
        y += Math.cos(Math.toRadians(heading)) * (dl + dr) / 2D;
    }
    
    private boolean checkForCollision(){
    	double currentAccelX = navX.getX();
    	double currentJerkX = currentAccelX - lastAccelX;
    	lastAccelX = currentAccelX;
    	double currentAccelY = navX.getY();
    	double currentJerkY = currentAccelY - lastAccelY;
    	lastAccelY = currentAccelY;
    	if(Math.abs(currentJerkX) > collisionThreshold || Math.abs(currentJerkY) > collisionThreshold){
    		return true;
    	}else{
    		return false;
    	}
    	
    	
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
    
    @Override
    public String toString() {
    	return "Position Logic";
    }

	@Override
	public boolean isTrustworhty() {
		return isTrustworthy;
	}

	@Override
	public void reset(double x, double y) {
		this.x = x;
		this.y = y;
		isTrustworthy = true;
		
	}
	
	@Override
	public void reset(){
		x = 0;
		y = 0;
		isTrustworthy = true;
		navX.resetYaw();
	}

	@Override
	public NAVX getNAVX() {
		return navX;
	}
    
    
}
