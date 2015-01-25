package com.gonna.die.subtasks;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;
import com.gonna.die.controller.Part;
import com.gonna.die.controller.PartType;

/**
 * Created by knifa on 25/01/15.
 */
public class BigSwitchesSubTask extends SubTask {

    public boolean[] switchesMustBe;

    public BigSwitchesSubTask(Module m) {
        super(m, "Calibrate Xenesulator");
        switchesMustBe = new boolean[3];
        for (int i = 0; i < 3; i++) {
            Part thing = Device.getInstance().getModulePartByType(this.module.getType(), PartType.SWITCH, i);
            this.switchesMustBe[i] = !thing.getDigitalValue();

            //Device.getInstance().getModulePartByType(this.module.getType(), PartType.LED, i).setDigitalValue(false);
        }
    }

    @Override
    public boolean isCompleted() {
        for (int i = 0; i < 3; i++) {
            boolean whatIsItNow = Device.getInstance().getModulePartByType(this.module.getType(), PartType.SWITCH, i).getDigitalValue();

            System.out.println(this.switchesMustBe[i]);
            System.out.println(whatIsItNow);
            System.out.println();

            if (whatIsItNow != this.switchesMustBe[i]) {
                System.out.println("REKT");
                return false;
            } else {
                //Device.getInstance().getModulePartByType(this.module.getType(), PartType.LED, i).setDigitalValue(true);
            }
        }
        System.out.println("we good");
        return true;
    }
}