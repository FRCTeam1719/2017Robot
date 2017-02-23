package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IPositionTracker;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.sensors.NAVX;

import edu.wpi.first.wpilibj.Timer;

public class MoveToPosUntilStop extends MoveToPosition{

	private final NAVX navX;
	private final Timer startupTimer = new Timer();
	private final double STARTUP_WAIT = 0.05;
	private boolean shouldRead;
	private double lastAccel;
	
	
	public MoveToPosUntilStop(double _desiredX, double _desiredY, IPositionTracker _posTracker, IDrive _drive,
			IRobot _robot, boolean _absolute) {
		super(_desiredX, _desiredY, _posTracker, _drive, _robot, _absolute);
		this.navX = _posTracker.getNAVX();
	}
	
	@Override
	public void initialize(){
		startupTimer.reset();
		startupTimer.start();
		shouldRead = false;
		super.initialize();
	}
	
	@Override
	public boolean isFinished(){
		if(shouldRead){
			double currentAccel = navX.getRawAccelY();
			//Check for sign flip
			if((lastAccel > 0 && currentAccel < 0) || (lastAccel < 0 && currentAccel > 0)){
				//Sign flipped, stop execution
				return true;
			}else{
				return super.isFinished();
			}
		}else{
			if(startupTimer.get() > STARTUP_WAIT){
				startupTimer.stop();
				shouldRead = true;
			}
			return super.isFinished();
		}
	}
	

}
