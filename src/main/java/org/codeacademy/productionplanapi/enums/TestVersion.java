package org.codeacademy.productionplanapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum TestVersion {
    V1,
    V2,
    V3,
    V4,
    V5;

    @JsonCreator
    public static TestVersion fromString(String key) {
        if (key == null) {
            return null;
        }

        for (TestVersion version : values()) {
            if (version.name().equalsIgnoreCase(key)) {
                return version;
            }
        }
        throw new IllegalArgumentException("Invalid TestVersion: " + key);
    }
}
