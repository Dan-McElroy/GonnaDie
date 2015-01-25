package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.Ship;
import com.gonna.die.components.BarComponent;
import com.gonna.die.components.TextureComponent;

/**
 * Created by Dan on 24/01/2015.
 */
public class BarSystem extends IteratingSystem {

    ComponentMapper<BarComponent> bcm;
    ComponentMapper<TextureComponent> tcm;
    private Ship ship;

    public BarSystem(Ship ship) {
        super(Family.getFor(BarComponent.class, TextureComponent.class));
        bcm = ComponentMapper.getFor(BarComponent.class);
        tcm = ComponentMapper.getFor(TextureComponent.class);
        this.ship = ship;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TextureComponent tc = tcm.get(entity);
        BarComponent bc = bcm.get(entity);
        tc.region.setRegionHeight((int) (160 * ship.rooms.get(bc.roomId).getHealthSegments()));
    }
}
