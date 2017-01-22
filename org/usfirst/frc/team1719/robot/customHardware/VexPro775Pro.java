package org.usfirst.frc.team1719.robot.customHardware;

import org.usfirst.frc.team1719.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;

public class VexPro775Pro implements SpeedController {
	UpdateLoop loop;

	public VexPro775Pro(SpeedController controlled, String motorName) {
		//Instantiate the update loop
		loop = new UpdateLoop(controlled, motorName);
		loop.run();
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

	@Override
	public void stopMotor() {
		loop.stopMotor();
	}

	public class UpdateLoop extends Thread implements SpeedController{
		SpeedController controlled;
		boolean shouldTry;
		int fails;
		final int MAX_ATTEMPTS = 3;
		private double currentSpeed;
		private String name;

		public UpdateLoop(SpeedController controlled, String name) {
			this.controlled = controlled;
			shouldTry = true;
			fails = 0;
			currentSpeed = 0d;
			this.name = name;
		}

		public void run() {
			while (true) {
				if (shouldTry && safeToDrive()) {
					controlled.set(currentSpeed);
				} else {
					// Error Condition Tripped!
					controlled.set(0);
					// Report the error
					System.out.println("Motor: " + name + " stalled! Halting! Will retry!");
					// Wait for retry
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fails++;
					if (fails > MAX_ATTEMPTS) {
						// We've failed, and need to stop
						shouldTry = false;
						// Log
						System.out.println("Motor: " + name + " has passed the retry threshold, disabling!");
					}
				}
			}
		}

		public boolean safeToDrive() {
			return RobotMap.pdp.getCurrent(1) <= 80;
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
		}

		@Override
		public void stopMotor() {
			currentSpeed = 0;
			controlled.stopMotor();
		}

	}

}
