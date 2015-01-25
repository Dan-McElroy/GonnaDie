package com.gonna.die.serial;

import java.util.HashMap;
import java.util.Map;
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

    public static Map<Integer, Double> analogChanges(ArduinoState old, ArduinoState next) {
        Map<Integer, Double> changes = new HashMap<>();
        for (int i = 0; i < ArduinoController.ANALOG_IN_COUNT; i++) {
            if (Math.abs(old.analogIn[i] - next.analogIn[i]) > 0.005) changes.put(i, next.analogIn[i]);
        }
        return changes;
    }

    public static Map<Integer, Boolean> digitalChanges(ArduinoState old, ArduinoState next) {
        Map<Integer, Boolean> changes = new HashMap<>();
        for (int i = 0; i < ArduinoController.DIGITAL_IN_COUNT; i++) {
            if (old.digitalIn[i] != next.digitalIn[i]) changes.put(i, next.digitalIn[i]);
        }
        return changes;
    }
}
