package com.gonna.die.subtasks;

import com.gonna.die.controller.Module;

/**
 * Created by knifa on 25/01/15.
 */
public class WiresSubTask extends SubTask {
    public WiresSubTask(Module m) {
        super(m, "Defuse Gravitic Detonator");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}