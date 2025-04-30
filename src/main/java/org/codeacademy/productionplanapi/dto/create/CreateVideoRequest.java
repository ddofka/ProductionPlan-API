package org.codeacademy.productionplanapi.dto.create;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateVideoRequest (
        @NotNull
        @FutureOrPresent
        LocalDate filmingStart,

        LocalDate editStart,

        @JsonSetter(nulls = Nulls.SKIP)
        @ValidEnum(enumClass = ProductionStage.class, message = "Invalid stage")
        @Nullable
        ProductionStage stage,

        @JsonSetter(nulls = Nulls.SKIP)
        @ValidEnum(enumClass = PostStatus.class, message = "Invalid stage")
        @Nullable
        PostStatus status,

        @JsonSetter(nulls = Nulls.SKIP)
        @ValidEnum(enumClass = Priority.class, message = "Invalid stage")
        @Nullable
        Priority priority,

        @NotBlank
        @Length(min = 3, max = 60)
        String compilationName,

        @Length(min = 8, max = 150)
        String referenceLink,

        @Length(min = 4, max = 300)
        String comment

){}
