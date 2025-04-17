package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.TestInformation;
import org.codeacademy.productionplanapi.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestInformationRepository extends JpaRepository<TestInformation, Long> {
    List<TestInformation> findAllByVideoId (Long id);
}
