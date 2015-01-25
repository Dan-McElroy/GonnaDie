package com.gonna.die.subtasks;

import com.gonna.die.controller.Module;

/**
 * Created by Dan on 24/01/2015.
 */
public abstract class SubTask {

    // Module interface
    public Module module;
    public String summary;      // Later based on actual task, for now random words

    public SubTask(Module m, String summary) {
        this.module = m;
        this.summary = summary;
    }

    public abstract boolean isCompleted();
}
