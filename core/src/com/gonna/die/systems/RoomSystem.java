package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.RoomComponent;
import com.gonna.die.components.TextureComponent;
import com.gonna.die.components.TimerComponent;

/**
 * Created by Dan on 25/01/2015.
 */

// To be used to render and destroy the rooms
public class RoomSystem extends IteratingSystem {

    private ComponentMapper<TextureComponent> tcm;
    private ComponentMapper<PositionComponent> pcm;
    private ComponentMapper<RoomComponent> rcm;

    public RoomSystem() {
        super(Family.getFor(TextureComponent.class, PositionComponent.class, RoomComponent.class));
        tcm = ComponentMapper.getFor(TextureComponent.class);
        pcm = ComponentMapper.getFor(PositionComponent.class);
        rcm = ComponentMapper.getFor(RoomComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TextureComponent tc = tcm.get(entity);
        PositionComponent pc = pcm.get(entity);
        RoomComponent rc = rcm.get(entity);
        /* if rc.room.isCritical() make it angry with tc */
        /* if rc.room has just been disabled, send it flying with pc */
        /* a while after this, destroy it */
    }
}
