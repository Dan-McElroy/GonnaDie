package com.gonna.die.serial;

import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortException;
public class SerialController {
    protected static SerialPort serialPort;

    public SerialController() {
        String[] portNames = SerialPortList.getPortNames();
        this.serialPort = new SerialPort(portNames[0]);

        try {
            this.serialPort.openPort();
            this.serialPort.setParams(
                    SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            this.serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            this.serialPort.addEventListener(new ArduinoSerialListener(this.serialPort));
        } catch (SerialPortException e) { }
    }

    public void close() {
        try {
            this.serialPort.closePort();
        } catch (SerialPortException e) { }
    }

    public static void main(String[] args) {
        SerialController sc = new SerialController();

        while (true) { }

        // sc.close();
    }
}