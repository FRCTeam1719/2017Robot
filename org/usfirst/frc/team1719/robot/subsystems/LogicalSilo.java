package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.ISilo;

import edu.wpi.first.wpilibj.Relay;

public class LogicalSilo implements ISilo {
	
	private Relay relay;
	
	public LogicalSilo(Relay relay){
		this.relay = relay;
	}

	@Override
	public void disable() {
		relay.set(Relay.Value.kOff);		
	}

	@Override
	public void setState (Relay.Value state) {
		relay.set(state);
		}

	@Override
	public Relay.Value getState() {
		return relay.get();
	}

}
