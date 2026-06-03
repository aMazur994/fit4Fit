package com.amaz.trainingassistantbackend.utils.enums;

public enum ShelterStatus {
    FULL, HAS_PLACE;

    @Override
    public String toString() {
        return this.name().equals("FULL") ? "Brak miejsc" : "Jest jeszcze wolne miejsce";
    }
}
