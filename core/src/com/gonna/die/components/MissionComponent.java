package com.gonna.die.components;

import com.badlogic.ashley.core.Component;

import java.util.Timer;

/**
 * Created by Dan on 24/01/2015.
 */
public class MissionComponent extends Component {
    public long duration;
    public long timeStarted;

    public long taskRate;
    public long lastTask;

}
