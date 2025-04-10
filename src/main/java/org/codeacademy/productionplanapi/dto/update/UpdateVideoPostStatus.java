package org.codeacademy.productionplanapi.dto.update;

import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.PostStatus;

public record UpdateVideoPostStatus(

        @ValidEnum(enumClass = PostStatus.class, message = "Invalid status")
        PostStatus status

) {}
