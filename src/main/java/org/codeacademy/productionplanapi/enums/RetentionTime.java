package org.codeacademy.productionplanapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum RetentionTime {
    THREE("3s"),
    FIFTEEN("15s"),
    THIRTY("30s"),
    FORTYFIVE("45s");

    private final String label;

    RetentionTime(String label) {
        this.label = label;
    }

    @JsonCreator
    public static String fromString(String key) {
        if (key == null) {
            return null;
        }
        for (RetentionTime t : RetentionTime.values()) {
            if (t.label.equals(key)) {
                return t.label;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + key);
    }
}
