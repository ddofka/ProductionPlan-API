package org.codeacademy.productionplanapi.dto.get;

import org.codeacademy.productionplanapi.enums.Role;

public record GetUserResponse(

        String username,
        String password,
        String email,
        Role role

){}
