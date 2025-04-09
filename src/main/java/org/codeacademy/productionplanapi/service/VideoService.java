package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Release;
import org.codeacademy.productionplanapi.entity.TestInformation;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.codeacademy.productionplanapi.exception.VideoNotFoundException;
import org.codeacademy.productionplanapi.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserService userService;
    private final EditorService editorService;
    private final DirectorService directorService;
    private final ReleaseService releaseService;
    private final TestInformationService testInformationService;

    public Video addVideo(Video video) {
        return videoRepository.saveAndFlush(video);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException("id=" + id));
    }

    public void addTestData() {
        PostStatus[] statuses = PostStatus.values();
        ProductionStage[] stages = ProductionStage.values();
        Priority[] priorities = Priority.values();

        for (int i = 1; i <= 15; i++) {
            Video video = new Video();
            video.setUser(userService.findUserById(1L));

            // Cycle through 3 directors (IDs: 1 to 3)
            video.setDirector(directorService.findDirectorById((long)((i - 1) % 3 + 1)));

            // Alternate between 2 editors (IDs: 1 and 2)
            video.setEditor(editorService.findEditorById((long)((i - 1) % 2 + 1)));

            // Cycle through PostStatus, ProductionStage, and Priority enums
            video.setStatus(statuses[(i - 1) % statuses.length]);
            video.setStage(stages[(i - 1) % stages.length]);
            video.setPriority(priorities[(i - 1) % priorities.length]);

            video.setCompilationName("Compilation " + i + ": Karma Project Part " + i);
            video.setFilmingStart(LocalDate.of(2025, 1, 1).plusDays(i));
            video.setEditStart(LocalDate.of(2025, 1, 15).plusDays(i));
            video.setReferenceLink("https://www.example.com/video/" + i);
            video.setComment("Test video #" + i);

            Video savedVideo = addVideo(video);

            Release release = new Release();
            release.setVideo(savedVideo);
            release.setSequence(1);
            release.setReleaseDateTime(LocalDateTime.of(2025, 4, 9, 11, 0).plusHours(i));
            releaseService.addRelease(release);
            if (i == 15){
                for (int j = 2; j <= 3; j++) {
                    Release release1 = new Release();
                    release1.setVideo(savedVideo);
                    release1.setSequence(j);
                    release1.setReleaseDateTime(LocalDateTime.of(2025, 4, 15, 10, 0).plusDays(j));
                    releaseService.addRelease(release1);
                }
            }

            TestInformation testInformation = new TestInformation();
            testInformation.setVideo(savedVideo);
            testInformation.setVersion("V1");
            testInformation.setTimeName("15s");
            testInformation.setTimeValue(55.33);
            testInformationService.addTestInformation(testInformation);
        }
    }


}
