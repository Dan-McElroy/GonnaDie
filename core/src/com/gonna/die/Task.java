package com.gonna.die;

import com.gonna.die.controller.Device;
import com.gonna.die.controller.Module;
import com.gonna.die.subtasks.SubTask;
import com.gonna.die.subtasks.SubTaskFactory;

import java.util.ArrayList;
import java.util.Random;

public final class Task {

    private static final int SECONDS_PER_SUBTASK = 8;

    public String message;
    public float expiration;
    private ArrayList<SubTask> subTasks;
    private Room room;
    public boolean started;

    public Task(String message, float expiration, Room room, ArrayList<Module> subTaskModules) {
        this.message = message;
        this.expiration = expiration;
        this.room = room;
        this.started = false;
        this.subTasks = new ArrayList<>();
        for (Module module : subTaskModules) {
            if (module.getPartOfTheGameBit())
                this.subTasks.add(SubTaskFactory.createModuleSubTask(module));
        }
    }

    public void start() {
        this.started = true;
    }

    public static Task getRandomTask(Ship ship) {
        Random rnd = new Random();
        Room room = ship.getRandomActiveRoom();
        String message = descriptions[rnd.nextInt(descriptions.length)];
        int noSubTasks = new Random().nextInt(4) + 1;
        ArrayList<Module> subTaskModules = Device.getInstance().getRandomModules(noSubTasks);

        float expiration = noSubTasks * SECONDS_PER_SUBTASK;
        System.out.println(expiration);
        System.out.println("Expiration: " + expiration + " | Sub Tasks: " + noSubTasks);
        return new Task(message, expiration, room, subTaskModules);
    }

    /* returns whether or not task has been completed */
    public boolean tick(float deltaTime) {
        if (started) {
            expiration -= deltaTime;
            if (expiration <= 0f) {
                room.currentHealth = Math.max(0, room.currentHealth - (10 * deltaTime));
            }

            if (subTasks.stream().allMatch(subTask -> subTask.isCompleted() == true)) {
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

    public String getSubTaskSummary() {
        String summary = "";
        for (SubTask st : subTasks) {
            summary += "\u2022 " + st.summary + "\n";
        }
        return summary;
    }
}
