package org.codeacademy.productionplanapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.create.CreateUserRequest;
import org.codeacademy.productionplanapi.dto.get.GetUserResponse;
import org.codeacademy.productionplanapi.dto.update.UserRoleAssign;
import org.codeacademy.productionplanapi.entity.Users;
import org.codeacademy.productionplanapi.mapper.UserMapper;
import org.codeacademy.productionplanapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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
        Users savedUser = userService.register(userMapper.dtoToUser(request, passwordEncoder));
        GetUserResponse response = userMapper.userToDto(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete user by id", description = "Deletes user by ID.")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get all users", description = "Retrieves all users.")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    @ApiResponse(responseCode = "204", description = "No users found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getUsers(){
        List<GetUserResponse> users = userMapper.userListToDto(userService.getAllUsers());
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Assign role", description = "Assign role to user by username.")
    @ApiResponse(responseCode = "200", description = "Role assigned successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/role")
    public ResponseEntity<GetUserResponse> assignRoleToUser(@RequestBody UserRoleAssign request){
        Users savedUser = userService.assignUserRole(request);
        GetUserResponse response = userMapper.userToDto(savedUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
