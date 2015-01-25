package com.gonna.die.subtasks;

import com.gonna.die.controller.Module;

/**
 * Created by knifa on 25/01/15.
 */
public class RotarySwitchesSubTask extends SubTask {
    public RotarySwitchesSubTask(Module m) {
        super(m, "Rotary Switches");
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}