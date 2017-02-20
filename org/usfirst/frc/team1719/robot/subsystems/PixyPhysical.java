package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.ISerial;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem wrapper for PixyLogic
 * @author Duncan
 */
public class PixyPhysical extends Subsystem implements IPixy {
    
    private class Updater extends Command {
        private long iter = 0;
        Updater(PixyPhysical subsys) {
            requires(subsys);
        }
        @Override
        public void execute() {
            if(iter++ % 10 == 0) {
                update();
            }
        }
        @Override
        protected boolean isFinished() {return false;}
    }
    
    private final PixyLogic logic;
    
    Updater updater;
    public PixyPhysical(ISerial serial) {
        logic = new PixyLogic(serial);
        updater = new Updater(this);
        updater.setRunWhenDisabled(true);
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
        setDefaultCommand(updater);
    }

	@Override
	public boolean hasBlocks() {
		return logic.hasBlocks();
	}

	@Override
	public boolean isTrustworthy() {
		return logic.isTrustworthy();
	}
	
	@Override
	public String toString() {
		return "Pixy Subsystem";
	}
    
}
