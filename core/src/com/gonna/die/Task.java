package com.gonna.die;

public final class Task {
    public String message;
    public long expiration; // TODO Maybe other stuff?

    public Task(String message, long expiration) {
        this.message = message;
        this.expiration = expiration;
    }
}
