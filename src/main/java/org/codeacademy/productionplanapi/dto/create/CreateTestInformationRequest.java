package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateTestInformationRequest(

        @NotNull(message = "Version is required")
        String version,
        @NotNull(message = "Retention is required")
        String retentionTime,
        @NotNull
        @DecimalMin(value = "0.00", inclusive = true, message = "Value must be at least 0.00")
        @DecimalMax(value = "100.00", inclusive = true, message = "Value must be at most 100.00")
        Double retentionValue

){}
