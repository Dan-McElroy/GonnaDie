package com.gonna.die.components;

import com.badlogic.ashley.core.Component;
import com.gonna.die.Task;

/**
 * Created by Dan on 25/01/2015.
 */
public class TimerComponent extends Component {

    public TimerComponent(Task task) {
        this.task = task;
    }

    public Task task;
}
