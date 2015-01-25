package com.gonna.die.subtasks;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;
import com.gonna.die.controller.Part;
import com.gonna.die.controller.PartType;
import com.gonna.die.serial.DigitalEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;

/**
 * Created by knifa on 25/01/15.
 */
public class PatternButtonsSubTask extends SubTask implements DigitalEventListener {

    public final int[] pattern;

    public ArrayList<Integer> userInputs;

    public Part[] buttons;

    public PatternButtonsSubTask(Module m) {
        super(m, "Pattern");
        pattern = new int[]{1,2,3,4};
        Device.getArduinoController().registerDigitalListener(this);
        buttons = new Part[4];
        for (int i = 0; i < 4; i++) {
            Device.getInstance().getModulePartByType(m.getType(), PartType.BUTTON, i);
        }
    }

    @Override
    public synchronized boolean isCompleted() {
        if (pattern.length != userInputs.size()) return false;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != userInputs.get(i)) return false;
        }
        return true;
    }

    public synchronized void digitalStateChanged(Map<Integer, Boolean> changes) {
        for (int i = 0; i < buttons.length; i++) {
            // If button is pressed
            if (changes.keySet().contains(buttons[i].getPin()) && changes.get(buttons[i].getPin())) {
                if (userInputs.size() == 4) userInputs.remove(0);
                userInputs.add(i);
            }
        }
    }
}