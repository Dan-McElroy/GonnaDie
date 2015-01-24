package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.Task;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.TextureComponent;
import com.gonna.die.components.TickerComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TickerSystem extends IteratingSystem {
    private final ComponentMapper<TickerComponent> tcm;
    private final ComponentMapper<TextureComponent> txm;

    public TickerSystem() {
        super(Family.getFor(TickerComponent.class, TextureComponent.class));
        tcm = ComponentMapper.getFor(TickerComponent.class);
        txm = ComponentMapper.getFor(TextureComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TickerComponent tc = tcm.get(entity);
        TextureComponent tx = txm.get(entity);

        long currentTime = System.currentTimeMillis();

        ArrayList<Task> tasks = new ArrayList<>();
        tc.tasks.stream().filter((task) -> task.expiration < currentTime).forEach((task) -> tasks.add(task));
        tc.tasks = tasks;

        String message = tc.tasks.stream()
                .map((task) -> task.message)
                .reduce("", (acc, msg) -> acc + msg + "\n");

        tx.text = message;
    }
}
