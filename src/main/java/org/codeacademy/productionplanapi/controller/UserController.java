package org.codeacademy.productionplanapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.create.CreateUserRequest;
import org.codeacademy.productionplanapi.dto.get.GetUserResponse;
import org.codeacademy.productionplanapi.entity.User;
import org.codeacademy.productionplanapi.mapper.UserMapper;
import org.codeacademy.productionplanapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Register new user", description = "Creates new user and returns response")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @PostMapping("/register")
    public ResponseEntity<GetUserResponse> registerUser(@Valid @RequestBody CreateUserRequest request) {
        User savedUser = userService.addUser(userMapper.dtoToUser(request, passwordEncoder));
        GetUserResponse response = userMapper.userToDto(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
