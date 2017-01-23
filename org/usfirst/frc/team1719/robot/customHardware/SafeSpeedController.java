package org.usfirst.frc.team1719.robot.customHardware;

import org.usfirst.frc.team1719.robot.interfaces.IPDP;

import edu.wpi.first.wpilibj.SpeedController;

public class SafeSpeedController implements SpeedController {
	UpdateLoop loop;

	public SafeSpeedController(SpeedController controlled, int port, String name, IPDP pdp) {
		loop = new UpdateLoop(controlled,port,pdp,name);
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
		private SpeedController controlled;
		private boolean shouldTry;
		private int fails;
		private final int MAX_ATTEMPTS = 3;
		private final int VOLTAGE_THRESHOLD = 80;
		private double currentSpeed;
		private final String name;
		private final int PORT;
		private IPDP pdp;

		public UpdateLoop(SpeedController controlled, int port, IPDP pdp, String name) {
			this.controlled = controlled;
			this.PORT = port;
			this.pdp = pdp;
			this.name = name;
			shouldTry = true;
			fails = 0;
			currentSpeed = 0d;
		}

		public void run() {
			while (shouldTry) {
				if (safeToDrive()) {
					controlled.set(currentSpeed);
				} else {
					// Error Condition Tripped!
					controlled.set(0);
					// Report the error
					System.out.println("Motor: " + name + " stalled! Halting! Will retry!");
					// Wait for retry
					fails++;
					if (fails > MAX_ATTEMPTS) {
						// We've failed, and need to stop
						shouldTry = false;
						// Log
						System.out.println("Motor: " + name + " has passed the retry threshold, disabling!");
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public boolean safeToDrive() {
			return pdp.getCurrent(PORT) <= VOLTAGE_THRESHOLD;
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
