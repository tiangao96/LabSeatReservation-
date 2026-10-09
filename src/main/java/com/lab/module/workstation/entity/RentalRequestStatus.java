package com.lab.module.workstation.entity;

public enum RentalRequestStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED;


    public boolean isPending() {
        return this == PENDING;
    }

    public boolean isTerminal() {
        return this != PENDING;
    }
}

