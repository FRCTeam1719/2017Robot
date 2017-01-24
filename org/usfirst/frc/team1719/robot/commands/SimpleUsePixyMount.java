package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SimpleUsePixyMount extends Command {

	private IPixy pixy;
	private IPixyMount mount;
	private final int Y_CENTER = 200 / 2;
	private final int X_CENTER = 320 / 2;
	private double x_cur;
	private int Y_cur;
	private int frame;

	public SimpleUsePixyMount(IPixy pixy, IPixyMount mount) {
		this.pixy = pixy;
		this.mount = mount;
		requires((Subsystem) mount);
		x_cur = X_CENTER;
		Y_cur = Y_CENTER;
		frame = 0;
	}

	@Override
	public void initialize() {
		frame = 0;
	}

	@Override
	public void execute() {
		if (frame % 10 == 0) {
			// Check for update
			boolean hasVal;
			int targetX = -1;
			int targetY = -1;
			synchronized (pixy) {
				if (pixy.hasBlocks()) {
					hasVal = true;
					targetX = pixy.getBlocks()[0].x;
					targetY = pixy.getBlocks()[0].y;
				} else {
					hasVal = false;
				}
			}
			// If there's an update, run w/ it
			if (hasVal) {
				int x_diff = targetX - X_CENTER;
				if(x_diff > 0){
					x_cur += 0.05;
				}else{
					x_cur -= 0.05;
				}
				System.out.println("x_diff" + x_diff);
				System.out.println("x_cur" + x_cur);
				mount.setX(x_cur);
			}else{
				mount.setX(0.5);
				x_cur = 0.5;
			}
		}
		frame++;

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
