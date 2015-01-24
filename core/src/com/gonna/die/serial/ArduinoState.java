package com.gonna.die.serial;

import java.util.Scanner;

public class ArduinoState {
    final public boolean[] digitalIn = new boolean[ArduinoController.DIGITAL_IN_COUNT];
    final public double[] analogIn = new double[ArduinoController.ANALOG_IN_COUNT];

    public ArduinoState(String dataString) {
        Scanner s = new Scanner(dataString);

        for (int i = 0; i < ArduinoController.DIGITAL_IN_COUNT; i++) {
            this.digitalIn[i] = s.nextInt() == 0 ? false : true;
        }

        for (int i = 0; i < ArduinoController.ANALOG_IN_COUNT; i++) {
            this.analogIn[i] = s.nextInt() / 1024.0;
        }
    }
}
