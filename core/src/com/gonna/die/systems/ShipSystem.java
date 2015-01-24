package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.Room;
import com.gonna.die.components.ShipComponent;

import java.util.ArrayList;

/**
 * Created by Dan on 24/01/2015.
 */
public class ShipSystem extends IteratingSystem {

    public ComponentMapper<ShipComponent> scm;

    public ShipSystem() {
        super(Family.getFor(ShipComponent.class));
        scm = ComponentMapper.getFor(ShipComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        ShipComponent sc = scm.get(entity);

        for (Room room : sc.rooms) {
            // Assuming delta measured in seconds.
            room.currentHealth -= Math.min(room.currentHealth, room.rateOfDecay * deltaTime);

            if (room.currentHealth == 0) {
                // End the game
            }
        }
    }
}
