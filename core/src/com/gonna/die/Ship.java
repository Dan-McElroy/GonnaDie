package com.gonna.die;

import java.util.ArrayList;
import java.util.Random;

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

    public Room getRoom(int id) {
        return rooms.get(id);
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

    private ArrayList<Room> activeRooms() {
        ArrayList<Room> activeRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.disabledState == Room.RoomState.ACTIVE) activeRooms.add(room);
        }
        return activeRooms;
    }

    public Room getRandomActiveRoom() {
        /* make array of active rooms */
        /* pick from random */

        ArrayList<Room> activeRooms = activeRooms();
        return activeRooms.get(new Random().nextInt(activeRooms.size()));
    }

    public void detachRoom() {
        Room weakestRoom = activeRooms().stream().min((room1, room2) -> Double.compare(room1.currentHealth, room2.currentHealth)).get();
        if (weakestRoom != null) {
            weakestRoom.disabledState = Room.RoomState.DISABLING;
        }
    }
}
