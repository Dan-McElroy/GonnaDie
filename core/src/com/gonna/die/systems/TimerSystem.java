package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.TextureComponent;
import com.gonna.die.components.TimerComponent;

import java.util.Timer;

/**
 * Created by Dan on 25/01/2015.
 */
public class TimerSystem extends IteratingSystem {

    private ComponentMapper<TextureComponent> txcm;
    private ComponentMapper<TimerComponent> tmcm;

    public TimerSystem() {
        super (Family.getFor(TextureComponent.class, TimerComponent.class, PositionComponent.class));
        txcm = ComponentMapper.getFor(TextureComponent.class);
        tmcm = ComponentMapper.getFor(TimerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent txc = txcm.get(entity);
        TimerComponent tmc = tmcm.get(entity);
        txc.text = String.format("%.2f s", (tmc.task != null) ? Math.max(tmc.task.expiration, 0f) : 0);
    }
}
