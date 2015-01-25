package com.gonna.die;

import com.gonna.die.controller.Module;

import java.util.Random;

/**
 * Created by Dan on 24/01/2015.
 */
public class SubTask {

    // Module interface
    public Module module;
    public String summary;      // Later based on actual task, for now random words

    public SubTask(Module module) {
        this.module = module;
        summary = summaries[new Random().nextInt(summaries.length)];
    }

    public boolean isCompleted() {
        /*
        check that god damn piece of shit module
        if that fucking fucker is cool, get wid it
         */
        return false;
    }

    private static String[] summaries = {
            " Fix it", "Bop it", "Push it", "Squeeze it", "Lick it", "Prod it", "Eat it"
    };
}
