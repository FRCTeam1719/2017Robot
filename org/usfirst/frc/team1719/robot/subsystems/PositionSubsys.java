package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.IGyro3D;

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
    private class Update extends Command {
        private Update() {}
        @Override
        protected void execute() {
            update();
        }
        @Override
        protected boolean isFinished() {
            return false;
        }
        
    }
    
    private final PositionLogic logic;
    
    public PositionSubsys(IGyro3D gyro, IEncoder left, IEncoder right) {
        logic = new PositionLogic(gyro, left, right);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Update());
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
}

