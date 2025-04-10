package org.codeacademy.productionplanapi.dto.get;

import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;

import java.time.LocalDate;
import java.util.List;

public record GetVideoResponse(

        List<GetReleaseResponse> releases,
        List<GetTestInformationResponse> tests,
        GetDirectorResponse director,
        GetEditorResponse editor,
        LocalDate filmingStart,
        LocalDate editStart,
        ProductionStage stage,
        PostStatus status,
        Priority priority,
        String compilationName,
        String referenceLink,
        String comment

){}
