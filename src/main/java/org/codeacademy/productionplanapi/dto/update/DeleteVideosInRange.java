package org.codeacademy.productionplanapi.dto.update;

import jakarta.validation.constraints.NotNull;

public record DeleteVideosInRange (
        @NotNull
        Long from,
        @NotNull
        Long to

){}
