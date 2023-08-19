package com.hussien.stcassessment.domain.enumerations;

public enum PermissionLevel {
    VIEW(1), EDIT(2);

    int value;

    private PermissionLevel(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
