package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gonna.die.BlueprintState;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.StateComponent;

public class BlueprintSystem extends IteratingSystem {
    ComponentMapper<StateComponent> scm;
    ComponentMapper<PositionComponent> pcm;
    public BlueprintSystem() {
        super(Family.getFor(StateComponent.class, PositionComponent.class));
        scm = ComponentMapper.getFor(StateComponent.class);
        pcm = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float delta) {
        StateComponent sc = scm.get(entity);
        PositionComponent pc = pcm.get(entity);

        if (BlueprintState.isMoving(sc.state)) {
            final int MAX_Y = 0;
            final int MIN_Y = -400;
            float y = pc.position.y;

            if ((sc.state == BlueprintState.BLUEPRINT_SCROLL_UP && y >= MAX_Y) ||
                (sc.state == BlueprintState.BLUEPRINT_SCROLL_DOWN && y <= MIN_Y)) {
                if (sc.state == BlueprintState.BLUEPRINT_SCROLL_UP) {
                    pc.position.y = MAX_Y;
                    sc.state = BlueprintState.BLUEPRINT_UP;
                System.out.println("up");
                } else {
                    pc.position.y = MIN_Y;
                    sc.state = BlueprintState.BLUEPRINT_DOWN;
                System.out.println("down");
                }
            } else {
                float dir;
                if (sc.state == BlueprintState.BLUEPRINT_SCROLL_UP) {
                    dir = 1000.0f;
                } else {
                    dir = -1000.0f;
                }
                System.out.printf("old y: %f;", y);
                pc.position.y = y + dir * delta;
                System.out.printf(" new y: %f;\n", pc.position.y);
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (sc.state == BlueprintState.BLUEPRINT_DOWN) {
                sc.state = BlueprintState.BLUEPRINT_SCROLL_UP;
                System.out.println("scroll up");
            } else {
                sc.state = BlueprintState.BLUEPRINT_SCROLL_DOWN;
                System.out.println("scroll down");
            }
        }
    }
}
