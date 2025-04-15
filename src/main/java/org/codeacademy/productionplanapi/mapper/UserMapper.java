package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateUserRequest;
import org.codeacademy.productionplanapi.dto.get.GetUserResponse;
import org.codeacademy.productionplanapi.entity.Users;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    GetUserResponse userToDto(Users users);

    List<GetUserResponse> userListToDto(List<Users> users);

    @Mapping(target = "password", qualifiedByName = "encodePassword")
    Users dtoToUser(CreateUserRequest request, @Context PasswordEncoder encoder);

    @Named("encodePassword")
    static String encodePassword(String raw, @Context PasswordEncoder encoder) {
        return encoder.encode(raw);
    }

}
