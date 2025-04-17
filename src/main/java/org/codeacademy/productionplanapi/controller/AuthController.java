package org.codeacademy.productionplanapi.controller;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.create.LoginRequest;
import org.codeacademy.productionplanapi.dto.get.LoginResponse;
import org.codeacademy.productionplanapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        String jwt = userService.verify(request);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
