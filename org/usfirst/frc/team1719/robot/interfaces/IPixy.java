package org.usfirst.frc.team1719.robot.interfaces;

import org.usfirst.frc.team1719.robot.subsystems.Block;

public interface IPixy extends GenericSubsystem {
    /**
     * @return the array of image data blocks from the most recently processed frame
     */
    public Block[] getBlocks();
    /**
     * Called each iteration to update the block array with new data.
     * @see <a href="http://www.cmucam.org/projects/cmucam5/wiki/Porting_Guide">The Official Pixy Porting Guide</a>
     */
    public void update();
}
