package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.MissionObserver;
import com.gonna.die.Ship;
import com.gonna.die.Task;
import com.gonna.die.components.MissionComponent;

import java.util.ArrayList;

/**
 * Created by Dan on 24/01/2015.
 */
public class MissionSystem extends IteratingSystem {

    private final ComponentMapper<MissionComponent> mcm;
    private ArrayList<MissionObserver> observers = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ship ship;

    public MissionSystem() {
        super(Family.getFor(MissionComponent.class));
        mcm = ComponentMapper.getFor(MissionComponent.class);
        ship = new Ship();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MissionComponent mc = mcm.get(entity);
        if (System.currentTimeMillis() - mc.lastTask >= mc.taskRate) {
            mc.lastTask = System.currentTimeMillis();
            if (tasks.size() < 4) {
                //Task task = new Task("TEST", 289089, sc.rooms.get(0));
                Task task = Task.getRandomTask(ship);
                tasks.add(task);
                task.start();
                System.out.println("task made: " + tasks.size());

                for (MissionObserver observer : observers) {
                    observer.taskCreated(task);
                }
            }
        }
        for (Task task : tasks) {
            //if (task.tick(deltaTime)) {
            //    tasks.remove(task);
            //}
        }
    }

    public void registerObserver(MissionObserver observer) {
        observers.add(observer);
    }
}
