package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.Assets;
import com.gonna.die.components.AlertComponent;
import com.gonna.die.components.TextureComponent;

public class AlertSystem extends IteratingSystem {
    ComponentMapper<TextureComponent> tcm;
    ComponentMapper<AlertComponent> acm;
    public AlertSystem() {
        super(Family.getFor(AlertComponent.class, TextureComponent.class));
        acm = ComponentMapper.getFor(AlertComponent.class);
        tcm = ComponentMapper.getFor(TextureComponent.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AlertComponent ac = acm.get(entity);
        TextureComponent tc = tcm.get(entity);
        if (ac.room.isCritical()) {
            tc.region = Assets.ALERT_SMALL;
        } else {
            tc.region = null;
        }

    }
}
