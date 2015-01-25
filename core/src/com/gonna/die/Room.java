package com.gonna.die;

/**
 * Created by Dan on 24/01/2015.
 */
public class Room {

    public static final int LIFE_SUPPORT = 0;
    public static final int REACTOR = 1;
    public static final int ENGINES = 2;
    public static final int BRIDGE = 3;
    public static final int TOTAL = 4;

    public double maxHealth;
    public double currentHealth;
    public boolean disabled = false;
    public int id;

    public Room(int id, double health) {
        this.maxHealth = health;
        this.currentHealth = health;
        this.id = id;
    }

    public double getHealthSegments() {
        return Math.ceil(8 * (currentHealth / maxHealth))/8;
    }

    public boolean isCritical() {
        return (!disabled && (currentHealth / maxHealth) < 0.2);
    }
}
