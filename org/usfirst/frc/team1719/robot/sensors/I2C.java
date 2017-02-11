package org.usfirst.frc.team1719.robot.sensors;

import java.nio.ByteBuffer;

import org.usfirst.frc.team1719.robot.interfaces.ISerial;

import edu.wpi.first.wpilibj.hal.I2CJNI;
import edu.wpi.first.wpilibj.util.BoundaryException;

public class I2C extends edu.wpi.first.wpilibj.I2C implements ISerial {
    
    private final Port prt;
    private final int addr;
    
    public I2C(Port port, int deviceAddress) {
        super(port, deviceAddress);
        prt = port;
        addr = deviceAddress;
    }
    
    @Override
    public synchronized byte[] read(int max) {
        if (max < 1) {
            throw new BoundaryException("Value must be at least 1, " + max + " given");
        }

        ByteBuffer dataReceivedBuffer = ByteBuffer.allocateDirect(max);

        int read = I2CJNI.i2CRead((byte) prt.value, (byte) addr, dataReceivedBuffer, (byte) max);
        byte[] ret = new byte[Math.max(read,0)];
        dataReceivedBuffer.get(ret);
        return ret;
    }
    
    @Override
    public synchronized int write(byte[] bytes, int max) {
        return super.writeBulk(bytes) ? max : 0;
    }
    
    @Override
    public synchronized boolean read(int registerAddress, int count, byte[] buffer) {
        if (count < 1) {
            throw new BoundaryException("Value must be at least 1, " + count + " given");
        }

        ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(1);
        dataToSendBuffer.put(new byte[] {(byte) registerAddress});

        if(I2CJNI.i2CWrite((byte) prt.value, (byte) addr, dataToSendBuffer, (byte) 1) < 1) return true;
        
        ByteBuffer dataReceivedBuffer = ByteBuffer.allocateDirect(count);
        int retVal = I2CJNI.i2CRead((byte) prt.value, (byte) addr, dataReceivedBuffer,
                                    (byte) count);
        dataReceivedBuffer.get(buffer);
        return retVal < count;
    }
}
