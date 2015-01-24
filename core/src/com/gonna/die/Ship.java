package com.gonna.die;

import java.util.ArrayList;

/**
 * Created by Dan on 24/01/2015.
 */
public class Ship {

    public ArrayList<Room> rooms;

    private static final int ROOM_HEALTH = 100;

    public Ship() {
        rooms = new ArrayList<>();
        for (int i = 0; i < Room.TOTAL; i++) {
            rooms.add(new Room(i, ROOM_HEALTH));
        }
    }

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
