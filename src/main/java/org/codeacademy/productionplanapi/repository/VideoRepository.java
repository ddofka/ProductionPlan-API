package org.codeacademy.productionplanapi.repository;

import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByStatus(PostStatus status, Pageable pageable);
    Page<Video> findAllByStage(ProductionStage stage, Pageable pageable);
    Page<Video> findAllByPriority(Priority priority, Pageable pageable);
    Page<Video> findAllByDirector_Name(String name, Pageable pageable);
    Page<Video> findAllByEditor_Name(String name, Pageable pageable);
}
