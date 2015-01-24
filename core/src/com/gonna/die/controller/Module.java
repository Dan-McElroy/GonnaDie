package com.gonna.die.controller;

import java.util.ArrayList;

public class Module {

    ArrayList<Part> parts;

    public Module(int moduleType) {

        this.parts = new ArrayList<Part>();
        this.createParts(moduleType);
    }

    public void createParts(int moduleType) {
        switch (moduleType) {
            /*
            * A0 module - Little switches
            * 4 x switches, 4 x LEDs.
            */
            case 0:
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                break;

            /*
            * B0 module - Big button
            * 1 x big button.
            */
            case 1:
                this.parts.add(new Part(PartType.BIG_BUTTON));
                break;


            /*
            * C0 module - Rotary switches
            * 2 x rotary switches, 2 x LEDs.
            */
            case 2:
                this.parts.add(new Part(PartType.ROTARY_SWITCH));
                this.parts.add(new Part(PartType.ROTARY_SWITCH));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                break;

            /*
            * A1 module - Big switches
            * 3 x switches, 3 x LEDs.
            */
            case 3:
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.SWITCH));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                break;

            /*
            * B1 module - Timer
            * 1 big circle LED ring, 1 small circle LED ring, 1 RGB LED.
            */
            case 4:
                this.parts.add(new Part(PartType.BIG_CIRCLE_LED));
                this.parts.add(new Part(PartType.LITTLE_CIRCLE_LED));
                this.parts.add(new Part(PartType.RGB_LED));
                break;

            /*
            * C1 module - Sliders et al.
            * 2 x sliders, 1 rotary switch, 5 x LEDs.
            */
            case 5:
                this.parts.add(new Part(PartType.SLIDER));
                this.parts.add(new Part(PartType.SLIDER));
                this.parts.add(new Part(PartType.ROTARY_SWITCH));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                break;

            /*
            * A2 module - Wires
            * 6 x LEDs.
            */
            case 6:
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                this.parts.add(new Part(PartType.LED));
                break;

            /*
            * B2 module - Main controls
            * 4 x buttons, 1 buzzer.
            */
            case 7:
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUZZER));
                break;

            /*
            * C2 module - Pattern buttons
            * 9 x buttons, 3 RGB LEDs.
            */
            case 8:
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.BUTTON));
                this.parts.add(new Part(PartType.RGB_LED));
                this.parts.add(new Part(PartType.RGB_LED));
                this.parts.add(new Part(PartType.RGB_LED));
                break;
        }
    }
}
