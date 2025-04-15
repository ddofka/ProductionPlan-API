package org.codeacademy.productionplanapi.controller;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.LoginRequest;
import org.codeacademy.productionplanapi.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String jwt = jwtService.generateToken((UserDetails) auth.getPrincipal());
        return ResponseEntity.ok(Map.of("token", jwt));
    }

}
