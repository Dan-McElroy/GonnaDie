package com.gonna.die.components;

import com.badlogic.ashley.core.Component;
import com.gonna.die.Task;

import java.util.ArrayList;

public class TickerComponent extends Component {
    public ArrayList<Task> tasks = new ArrayList<>();
}
