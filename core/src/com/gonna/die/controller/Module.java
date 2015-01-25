package com.gonna.die.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Module {
    private int type;

    ArrayList<Part> parts;
    Map<Integer, ArrayList<Part>> partsByType;

    private boolean partOfTheGameBit;

    public Module(int moduleType) {

        this.parts = new ArrayList<Part>();
        this.partsByType = new HashMap<>();
        this.type = moduleType;
        this.createParts(moduleType);
    }

    public void createParts(int moduleType) {
        switch (moduleType) {
            /*
            * A0 module - Little switches
            * 4 x switches, 4 x LEDs.
            */
            case ModuleType.LITTLE_SWITCHES:
                this.addPart(new Part(PartType.SWITCH, 0));
                this.addPart(new Part(PartType.SWITCH, 1));
                this.addPart(new Part(PartType.SWITCH, 2));
                this.addPart(new Part(PartType.SWITCH, 3));
                this.addPart(new Part(PartType.LED, 0));
                this.addPart(new Part(PartType.LED, 1));
                this.addPart(new Part(PartType.LED, 2));
                this.addPart(new Part(PartType.LED, 3));
                this.partOfTheGameBit = true;
                break;

            /*
            * B0 module - Big button
            * 1 x big button.
            */
            case 1:
                //this.addPart(new Part(PartType.SWITCH));
                //this.partOfTheGameBit = true;
                break;


            /*
            * C0 module - Rotary switches
            * 2 x rotary switches, 2 x LEDs.
            */
            case 2:
                /*this.addPart(new Part(PartType.POT));
                this.addPart(new Part(PartType.POT));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));*/
                this.partOfTheGameBit = true;
                break;

            /*
            * A1 module - Big switches
            * 3 x switches, 3 x LEDs.
            */
            case 3:
                /*this.addPart(new Part(PartType.SWITCH));
                this.addPart(new Part(PartType.SWITCH));
                this.addPart(new Part(PartType.SWITCH));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));*/
                this.partOfTheGameBit = true;
                break;

            /*
            * B1 module - Timer
            * 1 big circle LED ring, 1 small circle LED ring, 1 RGB LED.
            */
            case 4:
                //this.addPart(new Part(PartType.BIG_CIRCLE_LED));
                //this.addPart(new Part(PartType.LITTLE_CIRCLE_LED));
                //this.addPart(new Part(PartType.RGB_LED));
                break;

            /*
            * C1 module - Sliders et al.
            * 2 x sliders, 1 rotary switch, 5 x LEDs.
            */
            case 5:
                /*this.addPart(new Part(PartType.POT));
                this.addPart(new Part(PartType.POT));
                this.addPart(new Part(PartType.POT));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));*/
                this.partOfTheGameBit = true;
                break;

            /*
            * A2 module - Wires
            * 6 x LEDs.
            */
            case 6:
                /*this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));
                this.addPart(new Part(PartType.LED));*/
                this.partOfTheGameBit = true;
                break;

            /*
            * B2 module - Main controls
            * 4 x buttons, 1 buzzer.
            */
            case 7:
                /*this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));*/
                //this.addPart(new Part(PartType.BUZZER));
                break;

            /*
            * C2 module - Pattern buttons
            * 9 x buttons, 3 RGB LEDs.
            */
            case 8:
                /*this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                this.addPart(new Part(PartType.BUTTON));
                //this.addPart(new Part(PartType.RGB_LED));
                //this.addPart(new Part(PartType.RGB_LED));
                //this.addPart(new Part(PartType.RGB_LED));*/
                this.partOfTheGameBit = true;
                break;
        }
    }

    public int getType() {
        return this.type;
    }

    public boolean getPartOfTheGameBit() {
        return this.partOfTheGameBit;
    }

    private void addPart(Part part) {
        this.parts.add(part);

        if (!this.partsByType.containsKey(part.getType())) {
            this.partsByType.put(part.getType(), new ArrayList<>());
        }

        this.partsByType.get(part.getType()).add(part);
    }
}
