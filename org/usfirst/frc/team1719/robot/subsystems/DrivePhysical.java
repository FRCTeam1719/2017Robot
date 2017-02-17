package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseDrive;
import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.interfaces.IGyro3D;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * Wrapper around DriveLogic
 * @author aaron
 *
 */
public class DrivePhysical extends Subsystem implements IDrive {
    
    private DriveLogic drive;
    private IRobot robot;
    

    public DrivePhysical(SpeedController l, SpeedController r, ISolenoid shifter,
            IEncoder lEnc, IEncoder rEnc, Accelerometer acc, IGyro3D gyr, IRobot robot) {
        drive = new DriveLogic(l, r, shifter, lEnc, rEnc, acc, gyr);
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
    public void shift(IDrive.Shift state) {
        drive.shift(state);
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
	public void disable() {
		drive.disable();
	}
	
	@Override
	public String toString(){
		return "Drive Subsystem";
	}

}
