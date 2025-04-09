package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
