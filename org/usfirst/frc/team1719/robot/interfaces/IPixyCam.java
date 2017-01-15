package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public interface IPixyCam extends GenericSubsystem, PIDSource {

    static final double X_CTR = 160.0D;
    
	int getX();
	default void setPIDSourceType(PIDSourceType pidSource) {
	    
	}
	default PIDSourceType getPIDSourceType() {
        return null;
    }
	default double pidGet() {
        return (((double) getX()) - X_CTR) / X_CTR;
    }
}
