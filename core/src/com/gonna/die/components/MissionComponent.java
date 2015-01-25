package com.gonna.die.components;

import com.badlogic.ashley.core.Component;

import java.util.Timer;

/**
 * Created by Dan on 24/01/2015.
 */
public class MissionComponent extends Component {
    public double duration;
    public double timeStarted;

    public long taskRate;
    public long lastTask;

    public MissionComponent(long duration, long taskRate) {
        this.duration = duration;
        this.timeStarted = System.currentTimeMillis();
        this.taskRate = taskRate;
        this.lastTask = System.currentTimeMillis();
    }

}
