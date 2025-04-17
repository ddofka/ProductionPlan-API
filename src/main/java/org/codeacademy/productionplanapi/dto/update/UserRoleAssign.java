package org.codeacademy.productionplanapi.dto.update;

import jakarta.validation.constraints.NotBlank;
import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.Role;
import org.hibernate.validator.constraints.Length;

public record UserRoleAssign(

        @NotBlank(message = "Username is required")
        @Length(min = 4, max = 60)
        String username,
        @ValidEnum(enumClass = Role.class, message = "Invalid role")
        Role role

){}
