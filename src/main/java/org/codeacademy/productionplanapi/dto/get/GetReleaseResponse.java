package org.codeacademy.productionplanapi.dto.get;

import java.time.LocalDateTime;

public record GetReleaseResponse(

        LocalDateTime releaseDateTime,
        Integer part

){}
