package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.actuators.ISolenoid;
import org.usfirst.frc.team1719.robot.commands.UseDrive;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.INAVX;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.sensors.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.IGyro3D;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class DriveSubsys extends Subsystem implements IDrive {
    
    private DriveLogic drive;
    private IRobot robot;
    

    public DriveSubsys(SpeedController l, SpeedController r, ISolenoid shifter,
            IEncoder lEnc, IEncoder rEnc, Accelerometer acc, IGyro3D gyr, IRobot robot, INAVX navx) {
        drive = new DriveLogic(l, r, shifter, lEnc, rEnc, acc, gyr, navx);
        this.robot = robot;
     
    }

    @Override
    public void moveArcade(double spd, double dir) {
        drive.moveArcade(spd, dir);
    }

    @Override
    public void moveTank(double l, double r) {
        drive.moveTank(l, r);
    }

    @Override
    public void shift(boolean fast) {
        drive.shift(fast);
    }

    @Override
    public void setMaxSpeed(double spd) {
        drive.setMaxSpeed(spd);
    }

    @Override
    public IEncoder getEncoderL() {
        return drive.getEncoderL();
    }

    @Override
    public IEncoder getEncoderR() {
        return drive.getEncoderR();
    }

    @Override
    public Accelerometer getAccelerometer() {
        return drive.getAccelerometer();
    }

    @Override
    public IGyro3D getGyro() {
        return drive.getGyro();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseDrive(this, robot));
    }

	@Override
	public INAVX getNavX() {
		return drive.getNavX();
	}
}
