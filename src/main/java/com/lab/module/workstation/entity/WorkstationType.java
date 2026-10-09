package com.lab.module.workstation.entity;

public enum WorkstationType {
    SEAT,
    DOOR,
    WINDOW;


    public boolean isOccupiable() {
        return this == SEAT;
    }
}

