package com.fonon.landingserver.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaType {
    IMAGE("image"),
    VIDEO("video");

    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MediaType fromValue(String value) {
        for (MediaType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown media type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
