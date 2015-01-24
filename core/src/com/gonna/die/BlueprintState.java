package com.gonna.die;

public final class BlueprintState {
    public static final int BLUEPRINT_DOWN = 0;
    public static final int BLUEPRINT_UP = 1;
    public static final int BLUEPRINT_SCROLL_UP = 2;
    public static final int BLUEPRINT_SCROLL_DOWN = 3;

    public static boolean isMoving(int state) {
        return state == BLUEPRINT_SCROLL_UP || state == BLUEPRINT_SCROLL_DOWN;
    }
}
