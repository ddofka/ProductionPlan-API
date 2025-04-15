package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.UserPrincipal;
import org.codeacademy.productionplanapi.entity.Users;
import org.codeacademy.productionplanapi.exception.UserNotFoundException;
import org.codeacademy.productionplanapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
        return new UserPrincipal(user);
    }
}
