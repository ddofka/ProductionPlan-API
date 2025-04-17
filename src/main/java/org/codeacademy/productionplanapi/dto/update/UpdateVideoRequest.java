package org.codeacademy.productionplanapi.dto.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.dto.create.CreateReleaseRequest;
import org.codeacademy.productionplanapi.dto.create.CreateTestInformationRequest;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record UpdateVideoRequest(

        @Valid
        List<CreateReleaseRequest> releases,
        @Valid
        List<CreateTestInformationRequest> tests,
        @FutureOrPresent
        LocalDate filmingStart,
        LocalDate editStart,
        @Positive
        Long directorId,
        @Positive
        Long editorId,
        @ValidEnum(enumClass = ProductionStage.class, message = "Invalid status")
        ProductionStage stage,
        @ValidEnum(enumClass = PostStatus.class, message = "Invalid status")
        PostStatus status,
        @ValidEnum(enumClass = Priority.class, message = "Invalid status")
        Priority priority,
        @Length(min = 3, max = 60)
        String compilationName,
        @Length(min = 8, max = 150)
        String referenceLink,
        @Length(min = 4, max = 300)
        String comment

){}
