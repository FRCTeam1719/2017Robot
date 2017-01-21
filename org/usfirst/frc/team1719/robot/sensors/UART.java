package org.usfirst.frc.team1719.robot.sensors;

import org.usfirst.frc.team1719.robot.interfaces.ISerial;

import edu.wpi.first.wpilibj.SerialPort;

public class UART extends SerialPort implements ISerial {
    /**
     * Create an instance of a UART class. Defaults to 8 databits, no parity, and one stop
     * bit.
     *
     * @param baudRate The baud rate to configure the serial port.
     */
    public UART(int baudRate, Port port) {
        super(baudRate, port);
        // TODO Auto-generated constructor stub
    }
    /**
     * Create an instance of a UART class. Defaults to no parity and one stop bit.
     *
     * @param baudRate The baud rate to configure the serial port.
     * @param dataBits The number of data bits per transfer. Valid values are between 5 and 8 bits.
     */
    public UART(int baudRate, Port port, int dataBits) {
        super(baudRate, port, dataBits);
        // TODO Auto-generated constructor stub
    }
    /**
     * Create an instance of a UART class. Defaults to one stop bit.
     *
     * @param baudRate The baud rate to configure the serial port.
     * @param dataBits The number of data bits per transfer. Valid values are between 5 and 8 bits.
     * @param parity   Select the type of parity checking to use.
     */
    public UART(int baudRate, Port port, int dataBits, Parity parity) {
        super(baudRate, port, dataBits, parity);
        // TODO Auto-generated constructor stub
    }
    /**
     * Create an instance of a UART class.
     *
     * @param baudRate The baud rate to configure the serial port.
     * @param port     The Serial port to use
     * @param dataBits The number of data bits per transfer. Valid values are between 5 and 8 bits.
     * @param parity   Select the type of parity checking to use.
     * @param stopBits The number of stop bits to use as defined by the enum StopBits.
     */
    public UART(int baudRate, Port port, int dataBits, Parity parity, StopBits stopBits) {
        super(baudRate, port, dataBits, parity, stopBits);
        // TODO Auto-generated constructor stub
    }
    @Override
    public int available() {
        return super.getBytesReceived();
    }
    
}
