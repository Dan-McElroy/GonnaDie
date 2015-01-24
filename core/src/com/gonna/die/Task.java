package com.gonna.die;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;

import java.util.ArrayList;
import java.util.Random;

public final class Task {
    public String message;
    public long expiration; // TODO Maybe other stuff?
    private ArrayList<SubTask> subTasks;
    private Room room;
    public boolean started;

    public Task(String message, long expiration, Room room) {
        this.message = message;
        this.expiration = expiration;
        this.room = room;
        this.started = false;
        this.subTasks = new ArrayList<>();
        int noSubTasks = new Random().nextInt(4);
        ArrayList<Module> subTaskModules = Device.getInstance().getRandomModules(noSubTasks);
    }

    public void start() {
        this.started = true;
    }

    /* returns whether or not task has been completed */
    public boolean tick(float deltaTime) {
        expiration -= deltaTime;
        if (expiration <= 0f) {
            room.rateOfDecay += 10;
        }
        if (subTasks.stream().allMatch(subTask -> subTask.isCompleted())) {
            room.rateOfDecay -= 10;
            return true;
        };
        return false;
    }
}
