package subtasks;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;
import com.gonna.die.controller.Part;
import com.gonna.die.controller.PartType;

/**
 * Created by knifa on 25/01/15.
 */
public class LittleSwitchesSubTask extends SubTask {
    public boolean[] switchesMustBe;

    public LittleSwitchesSubTask(Module m) {
        super(m, "Little Switches");

        this.switchesMustBe = new boolean[4];
        for (int i = 0; i < 4; i++) {
            Part thing = Device.getInstance().getModulePartByType(this.module.getType(), PartType.SWITCH, i);
            this.switchesMustBe[i] = thing.getDigitalValue();
        }

        System.out.println("im new");
    }

    @Override
    public boolean isCompleted() {
        for (int i = 0; i < 4; i++) {
            boolean whatIsItNow = Device.getInstance().getModulePartByType(this.module.getType(), PartType.SWITCH, i).getDigitalValue();

            System.out.println(this.switchesMustBe[i]);
            System.out.println(whatIsItNow);
            System.out.println();

            if (whatIsItNow != this.switchesMustBe[i]) {
                System.out.println("REKT");
                return false;
            }
        }

        System.out.println("we good");
        return true;
    }
}
