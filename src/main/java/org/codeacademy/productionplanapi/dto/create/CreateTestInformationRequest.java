package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateTestInformationRequest(

        @Length(min = 2, max = 2)
        @NotBlank
        String version,
        @NotBlank
        @Length(min = 2, max = 3)
        String retentionTime,
        @NotNull
        @DecimalMin(value = "0.00", inclusive = true, message = "Value must be at least 0.00")
        @DecimalMax(value = "100.00", inclusive = true, message = "Value must be at most 100.00")
        Double retentionValue

){}
