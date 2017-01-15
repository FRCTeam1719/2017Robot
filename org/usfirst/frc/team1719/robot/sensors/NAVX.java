package org.usfirst.frc.team1719.robot.sensors;

import org.usfirst.frc.team1719.robot.interfaces.INAVX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSourceType;

public class NAVX extends AHRS implements INAVX {
    
    public NAVX(I2C.Port port) {
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
    public void resetPitch() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void resetRoll() {
    	
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
    public double pidGet() {
    	if (this.getPIDSourceType() == PIDSourceType.kRate) {
    		return getVelocityX();
    	}
    	else {
    		return getYaw();
    	}
    }
}
