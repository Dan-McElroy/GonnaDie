package com.gonna.die.subtasks;

        import com.gonna.die.controller.Module;

/**
 * Created by knifa on 25/01/15.
 */
public class SlidersSubTask extends SubTask {
    public SlidersSubTask(Module m) {
        super(m, "Sliders Sub Task");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}