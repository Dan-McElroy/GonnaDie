package com.gonna.die.controller;

import java.util.ArrayList;
import java.util.Arrays;

public class Device {

    Module[] modules;
    private static Device instance;

    private Device(){
        this.modules = new Module[9];
        this.createModules();
    }

    public static Device getInstance() {
        if (instance == null) {
            instance = new Device();
        }
        return instance;
    }

    public void createModules(){
        //A0 module - Little switches
        this.modules[ModuleType.LITTLE_SWITCHES] = new Module(ModuleType.LITTLE_SWITCHES);
        //B0 module - Big button
        this.modules[ModuleType.BIG_BUTTON] = new Module(ModuleType.BIG_BUTTON);
        //C0 module - Rotary switches
        this.modules[ModuleType.ROTARY_SWITCHES] = new Module(ModuleType.ROTARY_SWITCHES);
        //A1 module - Big switches
        this.modules[ModuleType.BIG_SWITCHES] = new Module(ModuleType.BIG_SWITCHES);
        //B1 module - Timer
        this.modules[ModuleType.TIME] = new Module(ModuleType.TIME);
        //C1 module - Sliders et al.
        this.modules[ModuleType.SLIDERS] = new Module(ModuleType.SLIDERS);
        //A2 module - Wires
        this.modules[ModuleType.WIRES] = new Module(ModuleType.WIRES);
        //B2 module - Main controls
        this.modules[ModuleType.MAIN_CONTROLS] = new Module(ModuleType.MAIN_CONTROLS);
        //C2 module - Pattern buttons
        this.modules[ModuleType.PATTERN_BUTTONS] = new Module(ModuleType.PATTERN_BUTTONS);
    }

    public ArrayList<Module> getRandomModules(int module){
        ArrayList<Module> modules = new ArrayList<Module>();
        ArrayList<Integer> moduleInts = new ArrayList<Integer>();

        ArrayList<Integer> potentialModuleInts = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );

        moduleInts.add(potentialModuleInts.get((int) Math.random() * potentialModuleInts.size()));

        for (int i = 0; i < module - 1; i++) {
            if (Math.random() > 0.5) {
                potentialModuleInts.removeAll(moduleInts);
                moduleInts.add(potentialModuleInts.get((int) Math.random() * potentialModuleInts.size()));
            }
        }

        for (int randomModuleInts: moduleInts){
            modules.add(this.modules[randomModuleInts]);
        }
        return modules;
    }
}
