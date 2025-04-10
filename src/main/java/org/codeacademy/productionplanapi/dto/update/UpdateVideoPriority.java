package org.codeacademy.productionplanapi.dto.update;

import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.Priority;

public record UpdateVideoPriority(

        @ValidEnum(enumClass = Priority.class, message = "Invalid priority")
        Priority priority

) {}
