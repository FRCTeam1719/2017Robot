package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.NAVX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Position-tracker subsystem wrapper.
 * @author Duncan
 */
public class PositionSubsys extends Subsystem implements IPositionTracker{
    /**
     * Pseudo-command to update position every iteration.
     * @author Duncan
     */
    
    private final PositionLogic logic;
    
    public PositionSubsys(NAVX navx, IEncoder left, IEncoder right) {
        logic = new PositionLogic(navx, left, right);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Update(this));
    }

    @Override
    public void disable() {/* Pure sensors are disable-safe */}

    @Override
    public void update() {
        logic.update();
    }

    @Override
    public double getX() {
        return logic.getX();
    }

    @Override
    public double getY() {
        return logic.getY();
    }

    @Override
    public double getHeading() {
        return logic.getHeading();
    }

	@Override
	public boolean isTrustworhty() {
		return logic.isTrustworhty();
	}

	@Override
	public void reset(double x, double y) {
		logic.reset(x, y);
	}
}

