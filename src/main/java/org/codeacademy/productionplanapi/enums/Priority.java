package org.codeacademy.productionplanapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Priority {

    HIGH,
    MEDIUM,
    LOW;

    @JsonCreator
    public static Priority fromValue(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return Priority.valueOf(value.toUpperCase());
    }

}
