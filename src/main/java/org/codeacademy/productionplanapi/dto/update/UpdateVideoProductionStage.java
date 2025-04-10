package org.codeacademy.productionplanapi.dto.update;

import org.codeacademy.productionplanapi.annotation.ValidEnum;
import org.codeacademy.productionplanapi.enums.ProductionStage;

public record UpdateVideoProductionStage(

        @ValidEnum(enumClass = ProductionStage.class, message = "Invalid stage")
        ProductionStage stage

) {}
