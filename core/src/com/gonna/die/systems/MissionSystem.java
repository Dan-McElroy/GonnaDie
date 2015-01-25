package com.gonna.die.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.gonna.die.MissionObserver;
import com.gonna.die.Ship;
import com.gonna.die.Task;
import com.gonna.die.components.MissionComponent;
import com.gonna.die.components.SoundComponent;
import com.gonna.die.controller.Device;
import com.gonna.die.controller.ModuleType;
import com.gonna.die.controller.Part;
import com.gonna.die.controller.PartType;
import com.gonna.die.serial.ArduinoState;
import com.gonna.die.serial.DigitalEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Dan on 24/01/2015.
 */
public class MissionSystem extends IteratingSystem implements DigitalEventListener {

    private final ComponentMapper<MissionComponent> mcm;
    private final ComponentMapper<SoundComponent> scm;
    private ArrayList<MissionObserver> observers = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    public Ship ship;

    public MissionSystem() {
        super(Family.getFor(MissionComponent.class, SoundComponent.class));
        mcm = ComponentMapper.getFor(MissionComponent.class);
        scm = ComponentMapper.getFor(SoundComponent.class);
        ship = new Ship();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MissionComponent mc = mcm.get(entity);
        SoundComponent sc = scm.get(entity);

        if (System.currentTimeMillis() - mc.lastTask >= mc.taskRate) {
            mc.lastTask = System.currentTimeMillis();
            if (tasks.size() < 4) {
                Task task = Task.getRandomTask(ship);
                tasks.add(task);
                task.start();

                for (MissionObserver observer : observers) {
                    observer.taskCreated(task);
                }
            }
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.tick(deltaTime)) {
                tasks.remove(task);
                for (MissionObserver observer : observers) {
                    observer.taskRemoved(task);
                }
            }
        }
        int noCriticalTasks = (int) tasks.stream().filter(task -> task.isCritical()).count();
        for (int i = 0; i < sc.sounds.size(); i++) {
            if (i <= noCriticalTasks) {
                if (!sc.sounds.get(i).isPlaying()) {
                    sc.sounds.get(i).setLooping(true);
                    sc.sounds.get(i).play();
                }
            } else {
                sc.sounds.get(i).stop();
            }
        }
    }

    public void registerObserver(MissionObserver observer) {
        observers.add(observer);
    }

    public void digitalStateChanged(Map<Integer, Boolean> changes) {
        Part bigButton = Device.getInstance().getModulePartByType(ModuleType.BIG_BUTTON, PartType.BUTTON, 0);
        if (changes.containsKey(bigButton.getPin()) && changes.get(bigButton.getPin())) {
            ship.detachRoom();
        }
    }
}
