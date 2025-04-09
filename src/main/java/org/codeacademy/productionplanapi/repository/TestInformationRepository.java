package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.TestInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInformationRepository extends JpaRepository<TestInformation, Long> {
    Page<TestInformation> findAllByRetentionTime(String timeName, Pageable pageable);
    Page<TestInformation> findAllByVideo_IdAndRetentionTime(Long videoId, String timeName, Pageable pageable);
}
