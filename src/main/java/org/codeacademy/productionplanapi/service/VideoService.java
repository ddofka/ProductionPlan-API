package org.codeacademy.productionplanapi.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeacademy.productionplanapi.dto.update.DeleteVideosInRange;
import org.codeacademy.productionplanapi.dto.update.UpdateVideoRequest;
import org.codeacademy.productionplanapi.entity.TestInformation;
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

import static org.codeacademy.productionplanapi.spec.VideoSpecification.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final ReleaseService releaseService;
    private final TestInformationService testInfoService;
    private final DirectorService directorService;
    private final EditorService editorService;

    public Video addVideo(Video video) {
        return videoRepository.saveAndFlush(video);
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
        if(!videoRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee with id: " + id + " not found");
        }
        videoRepository.deleteById(id);
    }

    @Transactional
    public void removeVideos(DeleteVideosInRange range) {
        if (videoRepository.findById(range.from()).isPresent()) {
            Long from = range.from();
            Long to = range.to();
            if (from <= to) {
                videoRepository.deleteAllByIdBetween(from, to);
                System.out.println("Deleted videos from ID " + from + " to " + to);
            }
            return;
        }
        throw new VideoNotFoundException("id=" + range.from() + " to " + range.to());
    }

    public Video updateVideoById(Long id, UpdateVideoRequest request) {
        Video videoFromDb = getVideoById(id);

        if (request.releases() == null){
            log.info("release is null");
        }

        if (request.releases() != null) {
            log.info("Updating release information");
            releaseService.updateReleases(videoFromDb, request.releases());
        }

        if (request.tests() != null) {
            log.info("Updating tests information");
            testInfoService.upodateTestInfos(videoFromDb, request.tests());
        }

        if(request.filmingStart() != null && !videoFromDb.getFilmingStart().equals(request.filmingStart())) {
            videoFromDb.setFilmingStart(request.filmingStart());
        }

        if (request.editStart() != null && !videoFromDb.getEditStart().equals(request.editStart())) {
            videoFromDb.setEditStart(request.editStart());
        }

        if(request.directorId() != null && videoFromDb.getDirector() == null) {
            videoFromDb.setDirector(directorService.findDirectorById(request.directorId()));
        }

        if(request.editorId() != null && videoFromDb.getEditor() == null) {
            videoFromDb.setEditor(editorService.findEditorById(request.editorId()));
        }

        if (request.stage() != null && !videoFromDb.getStage().equals(request.stage())) {
            videoFromDb.setStage(request.stage());
        }

        if (request.priority() != null && !videoFromDb.getPriority().equals(request.priority())) {
            videoFromDb.setPriority(request.priority());
        }

        if (StringUtils.isNotBlank(request.compilationName())
                && !videoFromDb.getCompilationName().equals(request.compilationName())) {
            videoFromDb.setCompilationName(request.compilationName());
        }

        if (StringUtils.isNotBlank(request.referenceLink())
                && !videoFromDb.getReferenceLink().equals(request.referenceLink())) {
            videoFromDb.setReferenceLink(request.referenceLink());
        }

        if (StringUtils.isNotBlank(request.comment())
                && !videoFromDb.getComment().equals(request.comment())) {
            videoFromDb.setComment(request.comment());
        }

        return addVideo(videoFromDb);
    }

}
