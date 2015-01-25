package com.gonna.die.serial;

import java.util.Map;

/**
 * Created by Dan on 25/01/2015.
 */
public interface DigitalEventListener {

    public void digitalStateChanged(Map<Integer, Boolean> changedInputs);
}
