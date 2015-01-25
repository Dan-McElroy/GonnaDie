package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.components.FlashyBoxComponent;
import com.gonna.die.components.PositionComponent;
import com.gonna.die.components.TextureComponent;

public class FlashyBoxSystem extends IteratingSystem {

    private final ComponentMapper<TextureComponent> tcm;
    private final ComponentMapper<FlashyBoxComponent> fbcm;

    public FlashyBoxSystem() {
        super(Family.getFor(FlashyBoxComponent.class, TextureComponent.class));
        tcm = ComponentMapper.getFor(TextureComponent.class);
        fbcm = ComponentMapper.getFor(FlashyBoxComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent tc = tcm.get(entity);
        FlashyBoxComponent fbc = fbcm.get(entity);
        long current = System.currentTimeMillis();
        if (tc.region == null) {
            if (Math.random() > 0.99) {
                tc.region = fbc.tr;
                fbc.onTime = current;
            }
        } else if (current - fbc.onTime > 2000) {
           if (Math.random() > 0.2)  {
               tc.region = null;
           }
        }
    }
}
