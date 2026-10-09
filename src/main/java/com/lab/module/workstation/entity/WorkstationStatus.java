package com.lab.module.workstation.entity;

import com.lab.module.workstation.entity.WorkstationStatus;

public enum WorkstationStatus {
    IDLE,
    PRE_RESERVED,
    OCCUPIED,
    MAINTENANCE,
    DISABLED;


    public boolean canReserve() {
        return this == IDLE;
    }

    public boolean canSeatOccupy() {
        return this == PRE_RESERVED;
    }

    public boolean canManageTo(WorkstationStatus target) {
        return switch (this) {
            case IDLE, OCCUPIED -> {
                if (target == MAINTENANCE || target == DISABLED) {
                    yield true;
                }
                yield false;
            }
            case PRE_RESERVED -> {
                if (target == IDLE || target == MAINTENANCE || target == DISABLED) {
                    yield true;
                }
                yield false;
            }
            case MAINTENANCE, DISABLED -> target == IDLE || target == MAINTENANCE || target == DISABLED;
        };
    }
}

