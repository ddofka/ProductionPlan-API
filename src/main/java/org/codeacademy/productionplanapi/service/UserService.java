package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.User;
import org.codeacademy.productionplanapi.enums.Role;
import org.codeacademy.productionplanapi.exception.UserNotFoundException;
import org.codeacademy.productionplanapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user){
        return userRepository.saveAndFlush(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id=" + id));
    }

    public void addTestUser(){
        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("password");
        user.setUsername("user");
        user.setRole(Role.ROLE_USER);
        addUser(user);
    }

}
