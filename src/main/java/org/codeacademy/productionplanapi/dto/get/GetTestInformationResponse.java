package org.codeacademy.productionplanapi.dto.get;

public record GetTestInformationResponse(

        String version,
        String retentionTime,
        Double retentionValue

){}
