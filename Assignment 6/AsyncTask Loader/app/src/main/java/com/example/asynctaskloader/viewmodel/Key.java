package com.example.asynctaskloader.viewmodel;

public class Key {
    private boolean is_lock;

    public Key() {
        is_lock = false;
    }

    public Key(boolean is_lock) {
        this.is_lock = is_lock;
    }

    public boolean isLock() {
        return is_lock;
    }

    public void lock() {
        this.is_lock = true;
    }

    public void unlock() {
        this.is_lock = false;
    }

    public void inverse() {
        this.is_lock = !this.is_lock;
    }
}
