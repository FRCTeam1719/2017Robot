package org.usfirst.frc.team1719.robot.interfaces;

public interface ISerial {
    int available();
    byte[] read(int max);
    int write(byte[] bytes, int max);
}
