package com.gonna.die.serial;

import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class ArduinoController implements Runnable {
    final public static int DIGITAL_IN_COUNT = 12;
    final public static int DIGITAL_OUT_COUNT = 12;
    final public static int ANALOG_IN_COUNT = 8;
    final public static int PWM_OUT_COUNT = 12;

    protected SerialPort serialPort;
    private boolean ready;

    private ArduinoState state;
    private LinkedList<QueuedCommand> commands;

    private ArrayList<AnalogEventListener> analogListeners;
    private ArrayList<DigitalEventListener> digitalListeners;

    private static class QueuedCommand {
        public byte[] data;
        public QueuedCommand(byte data[]) {
            this.data = data;
        }

    }

    public ArduinoController() {
        this.ready = false;
        this.commands = new LinkedList<>();
        this.analogListeners = new ArrayList<>();
        this.digitalListeners = new ArrayList<>();
    }

    public void setDigitalOut(int pin, boolean value) {
        byte[] data = new byte[3];
        data[0] = 'D';
        data[1] = (byte) pin;
        data[2] = (byte) (value == true ? 1 : 0);

        synchronized (this.commands) {
            this.commands.add(new QueuedCommand(data));
            this.commands.notifyAll();
        }
    }

    public void setPwmOut(int pin, byte value) {
        byte[] data = new byte[3];
        data[0] = 'P';
        data[1] = (byte) pin;
        data[2] = value;

        synchronized (this.commands) {
            this.commands.add(new QueuedCommand(data));
            this.commands.notifyAll();
        }
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
        Map<Integer, Double> analogChanges = ArduinoState.analogChanges(this.state, state);
        Map<Integer, Boolean> digitalChanges = ArduinoState.digitalChanges(this.state, state);

        if (analogChanges.size() > 0) {
            for (AnalogEventListener listener : analogListeners) {
                listener.analogStateChanged(analogChanges);
            }
        }
        if (digitalChanges.size() > 0) {
            for (DigitalEventListener listener : digitalListeners) {
                listener.digitalStateChanged(digitalChanges);
            }
        }
        this.state = state;
    }
    public ArduinoState getState() { return this.state; }

    public static void main(String[] args) {
        ArduinoController sc = new ArduinoController();
        sc.start();

        while (true) {
            if (sc.getState() != null) {
                sc.setPwmOut(0, (byte) (sc.getState().analogIn[0] * 255));
                System.out.println(sc.getState().analogIn[0]);
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}

        }
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    public void registerAnalogListener(AnalogEventListener listener) {
        analogListeners.add(listener);
    }

    public void registerDigitalListener(DigitalEventListener listener) {
        digitalListeners.add(listener);
    }

    @Override
    public void run() {
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

        while (!this.getReady()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }
        }

        while (true) {
            synchronized (this.commands) {
                try {
                    this.commands.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (this.commands.size() > 0) {
                    QueuedCommand q = this.commands.remove();
                    try {
                        this.serialPort.writeBytes(q.data);
                    } catch (SerialPortException e) { }
                }
            }
        }
    }
}