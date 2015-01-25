package com.gonna.die.subtasks;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;
import com.gonna.die.controller.Part;
import com.gonna.die.controller.PartType;

/**
 * Created by knifa on 25/01/15.
 */
public class WiresSubTask extends SubTask {
    public boolean[] wiresShouldBe;

    public WiresSubTask(Module m) {
        super(m, "Wires");

        /*switchesMustBe = new double[2];
        for (int i = 0; i < switchesMustBe.length; i++) {
            switchesMustBe[i] = Math.random();
            Part thing = Device.getInstance().getModulePartByType(this.module.getType(), PartType.POT, i);
            while (Math.abs(this.switchesMustBe[i] - thing.getAnalogValue()) < 0.3) {
                this.switchesMustBe[i] = Math.random();
            }
        }*/
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}