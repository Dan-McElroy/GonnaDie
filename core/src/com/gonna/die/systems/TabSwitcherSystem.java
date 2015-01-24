package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.gonna.die.TabState;
import com.gonna.die.components.IdComponent;
import com.gonna.die.components.StateComponent;
import com.gonna.die.components.TextureComponent;

import java.util.ArrayList;

public class TabSwitcherSystem extends IteratingSystem {
    ComponentMapper<TextureComponent> tcm;
    ComponentMapper<StateComponent> scm;
    ComponentMapper<IdComponent> icm;
    ArrayList<Entity> entities = new ArrayList<>();

    public TabSwitcherSystem() {
        super(Family.getFor(TextureComponent.class, StateComponent.class, IdComponent.class));
        tcm = ComponentMapper.getFor(TextureComponent.class);
        scm = ComponentMapper.getFor(StateComponent.class);
        icm = ComponentMapper.getFor(IdComponent.class);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        boolean up = Gdx.input.isKeyJustPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
        if (up || down) {
            for (int i = 0; i < entities.size(); i++) {
                Entity entity = entities.get(i);
                StateComponent sc = scm.get(entity);
                if (sc.state == TabState.SELECTED) {
                    sc.state = TabState.NOT_SELECTED;
                    if (up) {
                        int idx = i == 0 ? entities.size() - 1 : (i - 1) % entities.size();
                        StateComponent upSc = scm.get(entities.get(idx));
                        upSc.state = TabState.SELECTED;
                    } else {
                        StateComponent upSc = scm.get(entities.get(i + 1 % entities.size()));
                        upSc.state = TabState.SELECTED;
                    }
                    break;
                }
            }
        }
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (scm.get(entity).state != TabState.DISABLED) {
            entities.add(icm.get(entity).id, entity);
        }
    }
}
