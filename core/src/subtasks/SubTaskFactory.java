package subtasks;

import com.gonna.die.controller.Module;
import com.gonna.die.controller.ModuleType;

/**
 * Created by knifa on 25/01/15.
 */
public class SubTaskFactory {
    public static SubTask createModuleSubTask(Module m) {
        switch (m.getType()) {
            case ModuleType.LITTLE_SWITCHES:
                return new LittleSwitchesSubTask(m);

            case ModuleType.BIG_SWITCHES:
                return new BigSwitchesSubTask(m);

            case ModuleType.PATTERN_BUTTONS:
                return new PatternButtonsSubTask(m);

            case ModuleType.ROTARY_SWITCHES:
                return new RotarySwitchesSubTask(m);

            case ModuleType.SLIDERS:
                return new RotarySwitchesSubTask(m);

            case ModuleType.WIRES:
                return new WiresSubTask(m);
        }

        return null;
    }
}
