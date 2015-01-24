package com.gonna.die.serial;

import java.util.Scanner;

/**
 * Created by knifa on 24/01/15.
 */
public class ArduinoState {
    final public static int DIGITAL_IN_COUNT = 12;
    final public static int DIGITAL_OUT_COUNT = 12;
    final public static int ANALOG_IN_COUNT = 8;
    final public static int PWM_OUT_COUNT = 12;

    final public boolean[] digitalIn = new boolean[ArduinoState.DIGITAL_IN_COUNT];
    final public double[] analogIn = new double[ArduinoState.ANALOG_IN_COUNT];

    public ArduinoState(String dataString) {
        Scanner s = new Scanner(dataString);

        for (int i = 0; i < ArduinoState.DIGITAL_IN_COUNT; i++) {
            this.digitalIn[i] = s.nextInt() == 0 ? false : true;

            System.out.println(this.digitalIn[i]);
        }

        for (int i = 0; i < ArduinoState.ANALOG_IN_COUNT; i++) {
            this.analogIn[i] = s.nextInt() / 1024.0;

            System.out.println(this.analogIn[i]);
        }
    }
}
