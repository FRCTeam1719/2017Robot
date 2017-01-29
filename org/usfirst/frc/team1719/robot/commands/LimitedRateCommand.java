package org.usfirst.frc.team1719.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Abstract Command that can be used to create a command that executes less often than the RIO's polling rate.
 * @author aaron
 *
 */
public abstract class LimitedRateCommand extends Command{
	
	private final int RIO_POLLING_RATE = 100;
	private final int rate;
	private int frame = 0;
	
	/**
	 * @param executesPerSecond Desired number of executions per second
	 */
	public LimitedRateCommand(int executesPerSecond){
		rate = executesPerSecond;
	}
	
	@Override
	public void initialize(){
		frame = 1;
	}
	
	@Override
	public void execute(){
		if(frame%rate==0){
			action();
		}
		frame++;
	}
	
	/**
	 * Action you want to happen should be override this
	 */
	public abstract void action();
	
	
	
	
	

}
