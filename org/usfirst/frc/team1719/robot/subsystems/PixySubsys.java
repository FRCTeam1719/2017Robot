package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.ISerial;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem wrapper for PixyLogic
 * @author Duncan
 */
public class PixySubsys extends Subsystem implements IPixy {
    
    private class Updater extends Command {
        private long iter = 0;
        Updater(PixySubsys subsys) {
            requires(subsys);
        }
        @Override
        public void execute() {
            if(iter++ % 10 == 0) {/* Only poll camera at 10Hz = every 10 roboRIO cycles */
                update();
            }
        }
        @Override
        protected boolean isFinished() {return false;}
    }
    
    private final PixyLogic logic;
    
    public PixySubsys(ISerial serial) {
        logic = new PixyLogic(serial);
    }
    
    @Override
    public void disable() {/* No actuators */}
    
    @Override
    public Block[] getBlocks() {
        return logic.getBlocks();
    }
    
    @Override
    public void update() {
        logic.update();
    }
    
    @Override
    protected void initDefaultCommand() {
        /* Update the system every loop */
        setDefaultCommand(new Updater(this));
    }

	@Override
	public boolean hasBlocks() {
		return logic.hasBlocks();
	}

	@Override
	public boolean isTrustworthy() {
		return logic.isTrustworthy();
	}
    
}
