package org.codeacademy.productionplanapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum PostStatus {

    EDITING,
    TESTING,
    READY,
    POSTED;

    @JsonCreator
    public static PostStatus fromValue(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return PostStatus.valueOf(value.toUpperCase());
    }

}
