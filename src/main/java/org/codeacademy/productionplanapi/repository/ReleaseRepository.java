package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    List<Release> findAllByVideoId (Long id);
}
