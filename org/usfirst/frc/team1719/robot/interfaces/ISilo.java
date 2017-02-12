package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.Relay;

public interface ISilo extends GenericSubsystem{

	void setState(Relay.Value state);
	Relay.Value getState();
	
}
