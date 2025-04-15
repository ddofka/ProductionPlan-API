package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.User;
import org.codeacademy.productionplanapi.enums.Role;
import org.codeacademy.productionplanapi.exception.EmailAlreadyExistsException;
import org.codeacademy.productionplanapi.exception.UserNotFoundException;
import org.codeacademy.productionplanapi.exception.UsernameAlreadyExistsException;
import org.codeacademy.productionplanapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        if (user.getRole() == null ) {
            user.setRole(Role.ROLE_USER);
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username is already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already taken");
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.codeacademy.productionplanapi.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();
    }

}
