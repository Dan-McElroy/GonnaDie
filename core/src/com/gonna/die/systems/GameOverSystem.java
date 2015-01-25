package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.Ship;
import com.gonna.die.components.*;
import sun.plugin.com.event.COMEventHandler;

/**
 * Created by Dan on 25/01/2015.
 */
public class GameOverSystem extends IteratingSystem {

    private ComponentMapper<GameOverComponent> gocm;
    private ComponentMapper<TextureComponent> tcm;
    private ComponentMapper<PositionComponent> pcm;
    private ComponentMapper<AlphaComponent> acm;
    private Ship ship;

    public GameOverSystem(Ship ship) {
        super(Family.getFor(TextureComponent.class, GameOverComponent.class, PositionComponent.class));
        gocm = ComponentMapper.getFor(GameOverComponent.class);
        tcm = ComponentMapper.getFor(TextureComponent.class);
        pcm = ComponentMapper.getFor(PositionComponent.class);
        acm = ComponentMapper.getFor(AlphaComponent.class);
        this.ship = ship;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        AlphaComponent ac = acm.get(entity);
        if (ship.isGameOver()) {
            ac.alpha = 1.0f;
        }
    }
}
