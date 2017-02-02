package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Cycle through the list of GearHandler states
 * @author aaron
 *
 */
public class StepGearHandler extends InstantCommand{
	
	private static IGearHandler.gearStates[] order = 
		{IGearHandler.gearStates.RECIEVE,IGearHandler.gearStates.GRAB,IGearHandler.gearStates.TRANSPORT,
				IGearHandler.gearStates.POSITION, IGearHandler.gearStates.DELIVER};
	private IGearHandler handler;
	
	public StepGearHandler(IGearHandler handler){
		this.handler = handler;
	}
	
	@Override
	public void execute(){
		for(int i=0;i<order.length;i++){
			if(order[i]==handler.getState()){
				try{
					handler.setState(order[i+1]);
				}catch(ArrayIndexOutOfBoundsException e){
					//Wrap around
					handler.setState(order[0]);
				}
				break;
			}
		}
	}

	
}