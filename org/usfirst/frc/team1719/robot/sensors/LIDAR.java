package org.usfirst.frc.team1719.robot.sensors;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.Timer;
/**
 * LIDAR range finder 'driver' 
 */
public class LIDAR implements IRangeFinder {
    private I2C i2c;
    private volatile byte[] distance;
    private java.util.Timer updater;
    
    private static final int LIDAR_ADDR = 0x62;
    private static final int LIDAR_CONFIG_REGISTER = 0x00;
    private static final int LIDAR_STATUS_REGISTER = 0x01;
    private static final int LIDAR_DISTANCE_REGISTER = 0x8f;
    
    private static final int STATUS_FLAG_BUSY = 0x01;
    
    public LIDAR(I2C.Port port) {
        i2c = new I2C(port, LIDAR_ADDR);
        
        distance = new byte[2];
        
        updater = new java.util.Timer();
    }
    
    @Override
    public synchronized int getDistanceCM() {
        int var1 = distance[0];
        if(var1 < 0) var1 += 256;
        int var2 = distance[1];
        if(var2 < 0) var2 += 256;
        
        int var3 = (var1 << 8) + var2;
        
        return ((var3 == 0) ? -1 : var3);
    }

    // Start 10Hz polling
    public void start() {
        updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
    }
    
    // Start polling for period in milliseconds
    public void start(int period) {
        updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period);
    }
    
    public void stop() {
        updater.cancel();
        updater = new java.util.Timer();
    }
    
    // Update distance variable
    public synchronized void update() {
        i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
        byte status[] = new byte[1];
        do {
            //System.out.println("waiting for read");
            Timer.delay(0.04); // Delay for measurement to be taken
            i2c.read(LIDAR_STATUS_REGISTER, 1, status);
        } while((status[0] & STATUS_FLAG_BUSY) != 0);
        if(i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance)) System.out.println("No legal reading"); // Read in measurement
        System.out.println("MSB" + Byte.toString(distance[0]) + "LSB" +  Byte.toString(distance[1]));
        Timer.delay(0.005); // Delay to prevent over polling
    }
    
    // Timer task to keep distance updated
    private class LIDARUpdater extends TimerTask {
        public void run() {
            while(true) {
                update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
