package com.fonon.landingserver.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppointmentType {
    PERSONALITY("personality"),
    STORE("store");

    private final String value;

    AppointmentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AppointmentType fromValue(String value) {
        for (AppointmentType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown appointment type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
