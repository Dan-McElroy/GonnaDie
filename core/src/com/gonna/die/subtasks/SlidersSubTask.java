package com.gonna.die.subtasks;

        import com.gonna.die.controller.Device;
        import com.gonna.die.controller.Module;
        import com.gonna.die.controller.Part;
        import com.gonna.die.controller.PartType;

/**
 * Created by knifa on 25/01/15.
 */
public class SlidersSubTask extends SubTask {

    /* values range from 0 to 1 */
    public double[] slidersMustBe;

    public SlidersSubTask(Module m) {
        super(m, "Optimize Astropulser");
        slidersMustBe = new double[3];
        for (int i = 0; i < slidersMustBe.length; i++) {
            Part part = Device.getInstance().getModulePartByType(this.module.getType(), PartType.POT, i);
            this.slidersMustBe[i] = Math.random();
            while (Math.abs(this.slidersMustBe[i] - part.getAnalogValue()) < 0.3) this.slidersMustBe[i] = Math.random();
        }
        System.out.println("I'm also new");
    }

    @Override
    public boolean isCompleted() {
        for (int i = 0; i < slidersMustBe.length; i++) {
            double currentValue = Device.getInstance().getModulePartByType(this.module.getType(), PartType.POT, i).getAnalogValue();
            System.out.println(currentValue + " | " + slidersMustBe[i]);
            if (Math.abs(currentValue - slidersMustBe[i]) >= 0.03) {
                double pwmValue = Math.abs(currentValue - slidersMustBe[i]);
                Device.getInstance().getModulePartByType(this.module.getType(), PartType.LED, i).setPwmValue((float) pwmValue);

                return false;
            }
        }
        return true;
    }
}