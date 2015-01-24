package com.gonna.die.controller;

import com.gonna.die.serial.ArduinoController;

public class Part {
    private int type;
    private int pin;
    private ArduinoController ac;

    public Part(int partType, int pin) {
        this.type = partType;
        this.pin = pin;
        this.ac = Device.getArduinoController();
    }

    public void setDigitalValue(boolean value) {
        this.ac.setDigitalOut(this.pin, value);
    }

    public boolean getDigitalValue() {
        return this.ac.getState().digitalIn[this.pin];
    }

    public double getAnalogValue() {
        return this.ac.getState().analogIn[this.pin];
    }

    public int getType() { return this.type; }
    public int getPin() { return this.pin; }
}
