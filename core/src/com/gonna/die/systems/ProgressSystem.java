package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.components.MissionComponent;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.TextureComponent;

/**
 * Created by Dan on 25/01/2015.
 */
public class ProgressSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> pcm;
    private ComponentMapper<TextureComponent> tcm;
    private ComponentMapper<MissionComponent> mcm;

    private final int startPosX = 40;   // TO BE CHANGED
    private final int endPosX = 140;    // TO BE CHANGED

    public ProgressSystem() {
        super(Family.getFor(PositionComponent.class, TextureComponent.class, MissionComponent.class));
        pcm = ComponentMapper.getFor(PositionComponent.class);
        tcm = ComponentMapper.getFor(TextureComponent.class);
        mcm = ComponentMapper.getFor(MissionComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        PositionComponent pc = pcm.get(entity);
        TextureComponent tc = tcm.get(entity);
        MissionComponent mc = mcm.get(entity);

        pc.position.x = (((System.currentTimeMillis() - mc.timeStarted) / mc.duration) * (endPosX - startPosX)) + startPosX;

        if (Math.abs(mc.duration - (System.currentTimeMillis() - mc.timeStarted)) < 100) {
            /* end the game */
        }
    }
}
