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
     * @return the current heading of the robot (in degrees away from starting orientation)
     */
    public double getHeading();
}
