package org.codeacademy.productionplanapi.dto.create;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record CreateVideoRequest (

        List<CreateReleaseRequest> release,
        List<CreateTestInformationRequest> tests,
        @NotNull
        @Future
        LocalDate filmingStart,
        LocalDate editStart,
        @ValidEnum(enumClass = ProductionStage.class, message = "Invalid status")
        ProductionStage stage,
        @ValidEnum(enumClass = PostStatus.class, message = "Invalid status")
        PostStatus status,
        @ValidEnum(enumClass = Priority.class, message = "Invalid status")
        Priority priority,
        @NotBlank
        @Length(min = 3, max = 60)
        String compilationName,
        String referenceLink,
        String comment

){}
