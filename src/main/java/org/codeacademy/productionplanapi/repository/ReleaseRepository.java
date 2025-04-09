package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.Release;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Page<Release> findAllByReleaseDateTime (LocalDateTime releaseDateTime, Pageable pageable);
    Page<Release> findAllByVideo_IdAndReleaseDateTime (Long videoID,LocalDateTime releaseDateTime, Pageable pageable);
}
