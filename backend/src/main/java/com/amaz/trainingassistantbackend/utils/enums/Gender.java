package com.amaz.trainingassistantbackend.utils.enums;

public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        return this.name().equals("MALE") ? "Meska" : "Zenska";
    }
}
