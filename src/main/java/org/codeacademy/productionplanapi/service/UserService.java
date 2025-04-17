package org.codeacademy.productionplanapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.codeacademy.productionplanapi.dto.create.LoginRequest;
import org.codeacademy.productionplanapi.dto.update.UserRoleAssign;
import org.codeacademy.productionplanapi.entity.Users;
import org.codeacademy.productionplanapi.enums.Role;
import org.codeacademy.productionplanapi.exception.EmailAlreadyExistsException;
import org.codeacademy.productionplanapi.exception.UsernameAlreadyExistsException;
import org.codeacademy.productionplanapi.repository.UserRepository;
import org.codeacademy.productionplanapi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    public List<Users> getAllUsers(){
       return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id: " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public Users register(Users users) {
        if (users.getRole() == null ) {
            users.setRole(Role.ROLE_USER);
        }
        if (userRepository.existsByUsername(users.getUsername())) {
            throw new UsernameAlreadyExistsException("Username is already taken");
        }
        if (userRepository.existsByEmail(users.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already taken");
        }
        return userRepository.saveAndFlush(users);
    }

    public String verify(LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return jwtService.generateToken(userDetails);
    }

    public Users findByUsername(String name) {
        return userRepository.findUsersByUsername(name);
    }

    public Users assignUserRole(UserRoleAssign request) {
        Users userFromDb = findByUsername(request.username());
        if (userFromDb == null) {
            throw new EntityNotFoundException("Username is already taken");
        }
        userFromDb.setRole(request.role());
        return userRepository.saveAndFlush(userFromDb);
    }
}
