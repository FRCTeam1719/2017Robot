package org.usfirst.frc.team1719.robot.actuators;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * Our PWM LED brightness control driver.
 * @author Duncan
 */
public class LEDCtrl extends DigitalOutput implements ILEDCtrl {

    private static final double PWM_FREQ = 1000.0D;

    public LEDCtrl(int port) {
        super(port);
        enablePWM(0);
        setPWMRate(PWM_FREQ);
    }
    
    @Override
    public void setBrightness(double brightness) {
        updateDutyCycle(brightness / 5.0D);
    }
}
