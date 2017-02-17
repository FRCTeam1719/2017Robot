package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public interface IRobot {
	IOI getOI();
	IDashboard getDashboard();
	PowerDistributionPanel getPDP();
}
