package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Users;
import org.codeacademy.productionplanapi.enums.Role;
import org.codeacademy.productionplanapi.exception.EmailAlreadyExistsException;
import org.codeacademy.productionplanapi.exception.UsernameAlreadyExistsException;
import org.codeacademy.productionplanapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users addUser(Users users) {
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

}
