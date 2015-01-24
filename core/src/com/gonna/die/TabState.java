package com.gonna.die;

public class TabState {
    public static final int DISABLED = 0;
    public static final int NOT_SELECTED = 1;
    public static final int SELECTED = 2;

    public static int toggle(int state) {
        if (state == SELECTED) {
            return NOT_SELECTED;
        } else if (state == NOT_SELECTED) {
            return SELECTED;
        } else {
            return DISABLED;
        }
    }
}
