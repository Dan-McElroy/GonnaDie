package com.gonna.die.serial;

import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortException;
public class ArduinoController {
    final public static int DIGITAL_IN_COUNT = 12;
    final public static int DIGITAL_OUT_COUNT = 12;
    final public static int ANALOG_IN_COUNT = 8;
    final public static int PWM_OUT_COUNT = 12;

    protected SerialPort serialPort;
    private boolean ready;

    private ArduinoState state;

    public ArduinoController() {
        this.ready = false;

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
            this.serialPort.addEventListener(new ArduinoSerialListener(this));
        } catch (SerialPortException e) { }
    }

    public void setDigitalOut(int pin, boolean value) {
        byte[] data = new byte[3];
        data[0] = 'D';
        data[1] = (byte) pin;
        data[2] = (byte) (value == true ? 1 : 0);

        try {
            this.serialPort.writeBytes(data);
        } catch (SerialPortException e) { }
    }

    public void setPwmOut(int pin, byte value) {
        byte[] data = new byte[3];
        data[0] = 'P';
        data[1] = (byte) pin;
        data[2] = value;

        try {
            this.serialPort.writeBytes(data);
        } catch (SerialPortException e) { }
    }

    public void close() {
        try {
            this.serialPort.closePort();
        } catch (SerialPortException e) { }
    }

    protected SerialPort getSerialPort() { return this.serialPort; }
    protected void setReady(boolean ready) { this.ready = ready; }
    public boolean getReady() { return this.ready; }
    protected void setState(ArduinoState state) {
        this.state = state;
    }
    public ArduinoState getState() { return this.state; }

    public static void main(String[] args) {
        ArduinoController sc = new ArduinoController();

        while (true) {
            if (!sc.getReady()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) { }
                continue;
            }

            sc.setDigitalOut(0, true);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            sc.setDigitalOut(0, false);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

            if (sc.getState() != null)
                System.out.println(sc.getState().digitalIn[0]);
        }

        // sc.close();
    }
}