package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReleaseRequest (

        @NotNull
        @Future
        LocalDateTime releaseDateTime,
        @NotNull
        @Digits(integer = 2, fraction = 0)
        Integer part

){}
