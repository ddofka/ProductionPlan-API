package org.codeacademy.productionplanapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeacademy.productionplanapi.dto.create.CreateVideoRequest;
import org.codeacademy.productionplanapi.dto.get.GetReleaseResponse;
import org.codeacademy.productionplanapi.dto.get.GetTestInformationResponse;
import org.codeacademy.productionplanapi.dto.get.GetVideoResponse;
import org.codeacademy.productionplanapi.dto.update.UpdateVideoRequest;
import org.codeacademy.productionplanapi.dto.update.DeleteVideosInRange;
import org.codeacademy.productionplanapi.entity.Release;
import org.codeacademy.productionplanapi.entity.TestInformation;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.codeacademy.productionplanapi.mapper.ReleaseMapper;
import org.codeacademy.productionplanapi.mapper.TestInformationMapper;
import org.codeacademy.productionplanapi.mapper.VideoMapper;
import org.codeacademy.productionplanapi.service.ReleaseService;
import org.codeacademy.productionplanapi.service.TestInformationService;
import org.codeacademy.productionplanapi.service.UserService;
import org.codeacademy.productionplanapi.service.VideoService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final VideoMapper videoMapper;
    private final UserService userService;
    private final TestInformationService testInformationService;
    private final ReleaseService releaseService;
    private final TestInformationMapper testMapper;
    private final ReleaseMapper releaseMapper;

    @Operation(summary = "Get all videos", description = "Retrieves filtered videos.")
    @ApiResponse(responseCode = "200", description = "Video by filter retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Page<GetVideoResponse>> getFilteredVideos(
            @RequestParam(required = false) PostStatus status,
            @RequestParam(required = false) ProductionStage stage,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) String directorName,
            @RequestParam(required = false) String editorName,
            @RequestParam(required = false) String compilationName,
            @ParameterObject Pageable pageable
    ) {
        Page<Video> filteredVideos = videoService.getFilteredVideos(
                status, stage, priority, directorName, editorName, compilationName, pageable);
        Page<GetVideoResponse> response = videoMapper.videoPageToDto(filteredVideos);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get video by id", description = "Retrieves a video by id.")
    @ApiResponse(responseCode = "200", description = "Video retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Video found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<GetVideoResponse> getVideoById(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        GetVideoResponse response = videoMapper.videoToDto(video);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get video test by video id", description = "Retrieves a video tests by video id.")
    @ApiResponse(responseCode = "200", description = "Video tests retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Video tests found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @GetMapping("/{id}/tests")
    public ResponseEntity<List<GetTestInformationResponse>> getTestsByVideoId(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        List<TestInformation> testInformation =
                testInformationService.getTestInformationByVideoId(video.getTests().getFirst().getId());
        List<GetTestInformationResponse> response = testMapper.testInformationListToDto(testInformation);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get video releases by video id", description = "Retrieves a video release by video id.")
    @ApiResponse(responseCode = "200", description = "Video releases retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Video releases found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @GetMapping("/{id}/releases")
    public ResponseEntity<List<GetReleaseResponse>> getReleasesByVideoId(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        List<Release> releases =
                releaseService.getReleasesByVideoId(video.getReleases().getFirst().getId());
        List<GetReleaseResponse> response = releaseMapper.releaseListToDto(releases);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete the video", description = "Deletes the video by ID.")
    @ApiResponse(responseCode = "204", description = "Video deleted successfully")
    @ApiResponse(responseCode = "404", description = "Video not found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoById(@PathVariable Long id) {
        videoService.removeVideoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete the video", description = "Deletes the video by ID.")
    @ApiResponse(responseCode = "204", description = "Video deleted successfully")
    @ApiResponse(responseCode = "404", description = "Video not found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @DeleteMapping("/delete-range")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<Void> deleteVideos(@RequestBody DeleteVideosInRange range) {
      videoService.removeVideos(range);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Create the video", description = "Creates the video from request.")
    @ApiResponse(responseCode = "201", description = "Video created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @PostMapping
    public ResponseEntity<GetVideoResponse> createVideo(@Valid @RequestBody CreateVideoRequest request) {
        Video video = videoMapper.dtoToVideo(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        video.setUsers(userService.findByUsername(auth.getName()));
        Video savedVideo = videoService.addVideo(video);
        GetVideoResponse response = videoMapper.videoToDto(savedVideo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update the video", description = "Updates the video from request by id.")
    @ApiResponse(responseCode = "200", description = "Video updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<GetVideoResponse> updateVideo(
            @PathVariable Long id,
            @RequestBody @Valid UpdateVideoRequest request
    ){
        Video savedVideo = videoService.updateVideoById(id, request);
        GetVideoResponse response = videoMapper.videoToDto(savedVideo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //todo: to make sure that f.e edit start can't be before filming start etc.

}
