package org.codeacademy.productionplanapi.dto.update;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.annotation.Nullable;
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
        boolean clearFilmingStart,

        LocalDate editStart,
        boolean clearEditStart,

        @Positive
        Long directorId,
        boolean clearDirector,

        @Positive
        Long editorId,
        boolean clearEditor,

        @JsonSetter(nulls = Nulls.SKIP)
        @ValidEnum(enumClass = ProductionStage.class, message = "Invalid stage")
        @Nullable
        ProductionStage stage,
        boolean clearStage,

        @JsonSetter(nulls = Nulls.SKIP)
        @ValidEnum(enumClass = PostStatus.class, message = "Invalid status")
        @Nullable
        PostStatus status,
        boolean clearStatus,

        @JsonSetter(nulls = Nulls.SKIP)
        @ValidEnum(enumClass = Priority.class, message = "Invalid status")
        @Nullable
        Priority priority,
        boolean clearPriority,

        @Length(min = 3, max = 60)
        String compilationName,
        boolean clearCompilationName,

        @Length(min = 8, max = 150)
        String referenceLink,
        boolean clearReferenceLink,

        @Length(min = 4, max = 300)
        String comment,
        boolean clearComment

){}
