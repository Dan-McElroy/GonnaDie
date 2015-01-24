package com.gonna.die;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;

import java.util.ArrayList;
import java.util.Random;

public final class Task {

    private static final int SECONDS_PER_SUBTASK = 8;

    public String message;
    public long expiration; // TODO Maybe other stuff?
    private ArrayList<SubTask> subTasks;
    private Room room;
    public boolean started;

    public Task(String message, long expiration, Room room, ArrayList<Module> subTaskModules) {
        this.message = message;
        this.expiration = expiration;
        this.room = room;
        this.started = false;
        this.subTasks = new ArrayList<>();
        for (Module module : subTaskModules) {
            this.subTasks.add(new SubTask(module));
        }
    }

    public void start() {
        this.started = true;
    }

    public static Task getRandomTask(Ship ship) {
        Random rnd = new Random();
        Room room = ship.rooms.get(rnd.nextInt(Room.TOTAL));
        String message = descriptions[rnd.nextInt(descriptions.length)];
        int noSubTasks = new Random().nextInt(4);
        ArrayList<Module> subTaskModules = Device.getInstance().getRandomModules(noSubTasks);
        long expiration = noSubTasks * SECONDS_PER_SUBTASK;
        return new Task(message, expiration, room, subTaskModules);
    }

    /* returns whether or not task has been completed */
    public boolean tick(float deltaTime) {
        if (started) {
            expiration -= deltaTime;
            if (expiration <= 0f) {
                room.currentHealth -= 10 * deltaTime;     // this won't work - will add 10 every tick after
            }
            if (subTasks.stream().allMatch(subTask -> subTask.isCompleted())) {
                return true;
            };
        }
        return false;
    }

    public boolean isCritical() {
        return expiration <= 0f;
    }

    // THIS IS SUPER FUCKING GROSS FUCK THIS
    private static final String[] descriptions = {
        "Melt in the Thing", "Fire in the HowHaps", "Text in the field", "Chickens in the coop", "Catastrophe in the Collider"
    };
}
