package com.gonna.die.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Dan on 24/01/2015.
 */
public class BarComponent extends Component {

    public BarComponent(int roomId) {
        this.roomId = roomId;
    }

    public final int roomId;
}
