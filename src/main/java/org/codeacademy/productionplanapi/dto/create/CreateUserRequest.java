package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.Role;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(

        @NotBlank
        @Length(min = 4, max = 15)
        String username,
        @NotBlank
        @Length(min = 4, max = 30)
        String password,
        @Email
        String email,
        @ValidEnum(enumClass = Role.class, message = "Invalid role")
        Role role

){}
