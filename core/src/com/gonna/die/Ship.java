package com.gonna.die;

import java.util.ArrayList;

/**
 * Created by Dan on 24/01/2015.
 */
public class Ship {

    public ArrayList<Room> rooms;

    public boolean isGameOver() {
        for (Room room : rooms) {
            // Assuming delta measured in seconds.
            if (room.currentHealth <= 0) {
                return true;
            }
        }
        return false;
    }
}
