package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.TextureComponent;

import java.util.PriorityQueue;

public class RenderSystem extends IteratingSystem {
    SpriteBatch batch;
    ComponentMapper<TextureComponent> tcm;
    ComponentMapper<PositionComponent> pcm;
    PriorityQueue<Entity> entities;
    public RenderSystem() {
        super(Family.getFor(TextureComponent.class, PositionComponent.class));
        tcm = ComponentMapper.getFor(TextureComponent.class);
        pcm = ComponentMapper.getFor(PositionComponent.class);
        entities = new PriorityQueue<>(20, (Entity e1, Entity e2) ->
                (int)Math.signum(pcm.get(e1).position.z - pcm.get(e2).position.z));
        batch = new SpriteBatch();
    }
    @Override
    public void update(float delta) {
        super.update(delta);
        Entity entity;
        while ((entity = entities.poll()) != null) {
            TextureComponent tc = tcm.get(entity);
            PositionComponent pc = pcm.get(entity);
            batch.begin();
            batch.draw(tc.region, pc.position.x, pc.position.y);
            batch.end();
        }
    }
    @Override
    public void processEntity(Entity entity, float delta) {
        entities.add(entity);
    }
}
