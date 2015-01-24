package com.gonna.die.components;

import com.badlogic.ashley.core.Component;

public class IdComponent extends Component {
    public final int id;
    public IdComponent(int id) {
        this.id = id;
    }
}
