package com.gonna.die.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;


public class ArduinoSerialListener implements SerialPortEventListener {
    private ArduinoController ac;
    private SerialPort serialPort;

    private StringBuilder sb;

    public ArduinoSerialListener(ArduinoController ac) {
        this.ac = ac;
        this.serialPort = ac.getSerialPort();
        this.sb = new StringBuilder();
    }

    public void serialEvent(SerialPortEvent event) {
        if (event.getEventValue() <= 0) {
            return;
        }

        try {
            byte[] data = this.serialPort.readBytes();

            for (byte b : data) {
                if (b == 'H') {
                    this.serialPort.writeByte((byte) 'H');
                    this.sb.setLength(0);
                    this.ac.setReady(true);
                } else if (b == 'S') {
                    ArduinoState as = new ArduinoState(this.sb.toString());
                    this.ac.setState(as);
                    this.sb.setLength(0);
                } else {
                    this.sb.append((char) b);
                }
            }
        }
        catch (SerialPortException e) { }
    }
}