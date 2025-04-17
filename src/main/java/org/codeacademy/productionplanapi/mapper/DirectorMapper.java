package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateDirectorRequest;
import org.codeacademy.productionplanapi.dto.get.GetDirectorResponse;
import org.codeacademy.productionplanapi.entity.Director;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    GetDirectorResponse directorToDto(Director director);

    List<GetDirectorResponse> directorsListToDto(List<Director> directors);

    Director dtoToDirector(CreateDirectorRequest request);

}
