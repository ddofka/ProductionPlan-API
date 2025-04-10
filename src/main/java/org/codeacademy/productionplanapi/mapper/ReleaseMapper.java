package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateReleaseRequest;
import org.codeacademy.productionplanapi.dto.get.GetReleaseResponse;
import org.codeacademy.productionplanapi.entity.Release;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

    GetReleaseResponse releaseToDto(Release release);

    List<GetReleaseResponse> releaseListToDto(List<Release> releases);

    Release dtoToRelease(CreateReleaseRequest request);

}
