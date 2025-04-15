package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.codeacademy.productionplanapi.exception.VideoNotFoundException;
import org.codeacademy.productionplanapi.repository.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.codeacademy.productionplanapi.spec.VideoSpecification.*;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public Video addVideo(Video video) {
        return videoRepository.saveAndFlush(video);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Page<Video> getAllVideosPage(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public Page<Video> getFilteredVideos(
            PostStatus status,
            ProductionStage stage,
            Priority priority,
            String directorName,
            String editorName,
            String compilationName,
            Pageable pageable
    ) {
        Specification<Video> spec = Specification
                .where(hasStatus(status))
                .and(hasStage(stage))
                .and(hasPriority(priority))
                .and(hasDirectorName(directorName))
                .and(hasEditorName(editorName))
                .and(hasCompilationName(compilationName));
        return videoRepository.findAll(spec, pageable);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException("id=" + id));
    }

    public void removeVideoById(Long id) {
        videoRepository.deleteById(id);
    }

}
