package org.codeacademy.productionplanapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_EDITOR,
    ROLE_DIRECTOR;

    @JsonCreator
    public static Role fromValue(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return Role.valueOf(value.toUpperCase());
    }
}
