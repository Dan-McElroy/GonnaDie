package com.gonna.die;

/**
 * Created by Dan on 24/01/2015.
 */
public interface MissionObserver {

    public void taskCreated(Task task);
    public void taskRemoved(Task task);
}
