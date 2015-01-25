package com.gonna.die.controller;

import com.gonna.die.serial.ArduinoController;

import java.util.ArrayList;
import java.util.Arrays;

public class Device {

    Module[] modules;
    private static Device deviceInstance;
    private static ArduinoController arduinoController;

    private Device() {
        this.modules = new Module[9];
        this.createModules();
    }

    public static Device getInstance() {
        if (deviceInstance == null) {
            deviceInstance = new Device();
        }
        return deviceInstance;
    }

    public static ArduinoController getArduinoController() {
        if (arduinoController == null) {
            arduinoController = new ArduinoController();
            arduinoController.start();
            while (!arduinoController.getReady() || arduinoController.getState() == null) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {};
            }
        }
        return arduinoController;
    }

    public void createModules() {
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

    public ArrayList<Module> getRandomModules(int n) {
        ArrayList<Module> modules = new ArrayList<Module>();
        ArrayList<Module> potentialModules = new ArrayList<>();

        for (Module m : this.modules) {
            if (m.getPartOfTheGameBit())
                potentialModules.add(m);
        }

        for (int i = 0; i < n; i++) {
            int randIndex = (int) Math.random() * potentialModules.size();
            Module m = potentialModules.get(randIndex);
            potentialModules.remove(m);
            modules.add(m);
        }

        return modules;
    }

    public Part getModulePartByType(int mt, int pt, int index) {
        return this.modules[mt].partsByType.get(pt).get(index);
    }

    public static void main(String[] args) {
        Device d = Device.getInstance();

        while (d.getArduinoController().getReady()) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {};

            System.out.println(d.getModulePartByType(ModuleType.LITTLE_SWITCHES, PartType.SWITCH, 0).getDigitalValue());
        }
    }
}
