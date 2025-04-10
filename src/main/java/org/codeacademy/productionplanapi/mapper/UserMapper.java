package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateUserRequest;
import org.codeacademy.productionplanapi.dto.get.GetUserResponse;
import org.codeacademy.productionplanapi.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    GetUserResponse userToDto(User user);

    List<GetUserResponse> userListToDto(List<User> users);

    User dtoToUser(CreateUserRequest request);

}
