package org.usfirst.frc.team1719.robot.customHardware;

import org.usfirst.frc.team1719.robot.interfaces.IPDP;
import org.usfirst.frc.team1719.robot.sensors.UTimer;

import edu.wpi.first.wpilibj.SpeedController;

public class SafeSpeedController implements SpeedController {
	UpdateLoop loop;

	public SafeSpeedController(SpeedController controlled, int port, String name, IPDP pdp) {
		loop = new UpdateLoop(controlled,port,pdp,name);
		loop.start();
	}

	@Override
	public void pidWrite(double output) {
		loop.pidWrite(output);
	}

	@Override
	public double get() {
		return loop.get();
	}

	@Override
	public void set(double speed) {
		loop.set(speed);
	}

	@Override
	public void setInverted(boolean isInverted) {
		loop.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return loop.getInverted();
	}

	@Override
	public void disable() {
		loop.disable();
	}
	
	/**
	 * Re-enables the speedcontroller. NOTE: This does <b>NOT</b> re-enable the controller if it has
	 * passed the acceptable number of retries
	 */
	public void enable(){
		loop.enable();
	}

	@Override
	public void stopMotor() {
		loop.stopMotor();
	}

	public class UpdateLoop extends Thread implements SpeedController{
		private SpeedController controlled;
		private boolean shouldTry;
		private int fails;
		private final int MAX_ATTEMPTS = 3;
		private final double DANGER_AMPS = 130;
		private final double WARNING_AMPS = 100;
		private final double COOLDOWN_TIME = 100;
		private volatile double currentSpeed;
		private final String name;
		private final int PORT;
		private IPDP pdp;
		private UTimer cooldown = new UTimer();
		private double[] readings = new double[40];
		private int count = 0;
		private final int MAX_LIST = 40+1;
		private final double WARNING_THRESHOLD = 0.75;

		public UpdateLoop(SpeedController controlled, int port, IPDP pdp, String name) {
			super(name);
			this.controlled = controlled;
			this.PORT = port;
			this.pdp = pdp;
			this.name = name;
			shouldTry = true;
			fails = 0;
			currentSpeed = 0d;
		}

		public void run() {
			while(shouldTry){
				if(cooldown.isSet()){
					//We're disabled
					if(cooldown.get()>=COOLDOWN_TIME){
						cooldown.stop();
						cooldown.reset();
					}
				}else{
					//Get the current reading
					double current = pdp.getCurrent(PORT);
					updateReadings(current);
					if(current>=DANGER_AMPS || isDangerous()){
						//We need to cooldown
						controlled.set(0);
						cooldown.start();
						fails++;
						System.out.println("VEXPRO " + name + " IS TOO HOT! COOLING DOWN!");
						if(fails>MAX_ATTEMPTS){
							shouldTry = false;
							System.out.println("VEXPRO " + name + " IS TOO DANGEROUS! SHUTTING DOWN!");
						}
					}else{
						//We're good
						controlled.set(currentSpeed);
					}
					
				}
			}
		}

		//Update the readings List by forcing out the old entry & adding a new one
		private void updateReadings(double newReading){
			readings[count] = newReading;
			if(count<MAX_LIST){
				count++;
			}
		}
		
		private boolean isDangerous(){
			int good = 0;
			for(int i=0;i<readings.length;i++){
				if(readings[i]<WARNING_AMPS){
					good++;
				}
			}
			return (good/readings.length) > WARNING_THRESHOLD;
		}

		@Override
		public void pidWrite(double output) {
			controlled.pidWrite(output);
		}

		@Override
		public double get() {
			return controlled.get();
		}

		@Override
		public void set(double speed) {
			currentSpeed = speed;
		}

		@Override
		public void setInverted(boolean isInverted) {
			controlled.setInverted(isInverted);
		}

		@Override
		public boolean getInverted() {
			return controlled.getInverted();
		}

		@Override
		public void disable() {
			controlled.disable();
			currentSpeed = 0;
			shouldTry = false;
		}
		
		public void enable(){
			controlled.disable();
			shouldTry = true;
		}

		@Override
		public void stopMotor() {
			currentSpeed = 0;
			controlled.stopMotor();
		}

	}

}
