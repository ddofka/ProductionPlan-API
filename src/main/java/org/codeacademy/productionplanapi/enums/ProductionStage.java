package org.codeacademy.productionplanapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ProductionStage {

    FILMING,
    DONE_FILMING,
    CANCELED,
    PREP,
    REFILM;

    @JsonCreator
    public static ProductionStage fromValue(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return ProductionStage.valueOf(value.toUpperCase());
    }

}
