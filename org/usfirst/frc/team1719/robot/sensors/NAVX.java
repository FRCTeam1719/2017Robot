package org.usfirst.frc.team1719.robot.sensors;

import org.usfirst.frc.team1719.robot.interfaces.IGyro3D;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

//TODO: FINISH IMPLEMENTATION
public class NAVX extends AHRS implements IGyro3D, Accelerometer {
    
    public NAVX(I2C.Port port) {
        super(port);
        
    }
    
    public NAVX(SerialPort.Port port){
        super(port);
    }
    
    @Override
    public void setRange(Range range) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public double getX() {
        return getWorldLinearAccelX();
    }
    
    @Override
    public double getY() {
        return getWorldLinearAccelY();
    }
    
    @Override
    public double getZ() {
        return getWorldLinearAccelZ();
    }
    
    
    @Override
    public void resetRoll() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void resetYaw() {
        reset();
    }
    
    @Override
    public double getPitchRate() {
        return getRawGyroX();
    }
    
    @Override
    public double getRollRate() {
        return getRawGyroY();
    }
    
    @Override
    public double getYawRate() {
        return getRate();
    }

	@Override
	public void resetPitch() {
		// TODO Auto-generated method stub
		
	}
    
}
