package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.gonna.die.components.AlphaComponent;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.TextureComponent;

import java.util.PriorityQueue;

public class RenderSystem extends IteratingSystem {
    SpriteBatch batch;
    ComponentMapper<TextureComponent> tcm;
    ComponentMapper<PositionComponent> pcm;
    ComponentMapper<AlphaComponent> acm;
    PriorityQueue<Entity> entities;

    public RenderSystem() {
        super(Family.getFor(TextureComponent.class, PositionComponent.class));
        tcm = ComponentMapper.getFor(TextureComponent.class);
        pcm = ComponentMapper.getFor(PositionComponent.class);
        acm = ComponentMapper.getFor(AlphaComponent.class);
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
            if (tc.region != null) {
                AlphaComponent ac = acm.get(entity);
                if (ac != null) {
                    Color color = batch.getColor();
                    color.a = ac.alpha;
                    batch.setColor(color);
                }
                batch.draw(tc.region, pc.position.x, pc.position.y);
                if (ac != null) {
                    Color color = batch.getColor();
                    color.a = 1.0f;
                    batch.setColor(color);
                }
            } else if (tc.text != null) {
                //float height = font.getWrappedBounds(tc.text, tc.width).height;
                // TODO Supposedly slow, use FrameBuffer?
                //font.drawWrapped(batch, tc.text, pc.position.x, pc.position.y - tc.height + height, tc.width);
                String displayText = tc.tickIn ? tc.text.substring(0,
                        (int) Math.min((int)(tc.text.length()  * (System.currentTimeMillis() - tc.timeShown) / 1000), tc.text.length()))
                        : tc.text;
                tc.font.drawMultiLine(batch, displayText, pc.position.x, pc.position.y);
            }
            batch.end();
        }
    }
    @Override
    public void processEntity(Entity entity, float delta) {
        entities.add(entity);
    }
}
