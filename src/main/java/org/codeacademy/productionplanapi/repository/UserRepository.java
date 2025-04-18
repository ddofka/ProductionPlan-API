package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Users findUsersByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    List<Users> getUsersById(Long id);

    Users getUserById(Long userId);
}
