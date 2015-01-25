package com.gonna.die.serial;

import java.util.Map;

/**
 * Created by Dan on 25/01/2015.
 */
public interface AnalogEventListener {

    // Maps positions to changed values
    public void analogStateChanged(Map<Integer, Double> changedInputs);
}
