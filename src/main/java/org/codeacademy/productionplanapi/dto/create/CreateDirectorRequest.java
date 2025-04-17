package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateDirectorRequest(
       @Length(min = 4, max = 20)
       @NotBlank
       String name,
       @Length(min = 4, max = 56)
       @NotBlank
       String country
){}
