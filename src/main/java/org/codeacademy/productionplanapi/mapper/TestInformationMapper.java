package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateTestInformationRequest;
import org.codeacademy.productionplanapi.dto.get.GetTestInformationResponse;
import org.codeacademy.productionplanapi.entity.TestInformation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestInformationMapper {

    GetTestInformationResponse testInformationToDto(TestInformation testInformation);

    List<GetTestInformationResponse> testInformationListToDto(List<TestInformation> tests);

    TestInformation dtoToTestInformation(CreateTestInformationRequest request);
}
