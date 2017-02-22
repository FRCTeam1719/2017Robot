package org.usfirst.frc.team1719.robot.interfaces;

/**
 * Actuator-void subsystem used for tracking the position on the field.
 * Position (0,0) at heading 0 is the starting position of the robot.
 * @author Duncan
 */
public interface IPositionTracker extends GenericSubsystem {
    /**
     * Called every iteration to update the tracker's perception of the position of the robot.
     */
    public void update();
    
    /**
     * @return the current x (right-left axis from starting position) position of the robot
     */
    public double getX();
    
    /**
     * @return the current y (fore-aft axis from starting position) position of the robot
     */
    public double getY();
    
    /**
     * Reset the Positioning System to a known good location
     * @param x
     * @param y
     */
    public void reset(double x, double y);
    
    /**
     * Reset the positioning system to zero
     */
    public void reset();
    
    /**
     * @return the current heading of the robot (in degrees away from starting orientation)
     */
    public double getHeading();
    
    /**
     * @return whether or not the robot thinks the position reported is trustworthy
     */
    public boolean isTrustworhty();
}
