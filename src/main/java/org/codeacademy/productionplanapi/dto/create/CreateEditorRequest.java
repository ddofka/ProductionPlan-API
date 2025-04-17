package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateEditorRequest(
        @Length(min = 4, max = 20)
        @NotBlank
        String name,
        boolean isFreelancer

){}
