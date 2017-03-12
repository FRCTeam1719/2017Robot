package org.usfirst.frc.team1719.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command{
    private final double time;
    private Timer timer;
    
    public Delay(double time) {
        this.time = time;
    }
    
    @Override
    public void initialize(){
        timer = new Timer();
        timer.start();
    }
    
    @Override
    public boolean isFinished(){
        return timer.get() > time;
    }
    
    @Override
    public void end(){
        timer.stop();
    }
    
    
    
}
