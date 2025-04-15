package org.codeacademy.productionplanapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.get.GetVideoResponse;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;
import org.codeacademy.productionplanapi.mapper.VideoMapper;
import org.codeacademy.productionplanapi.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final VideoMapper videoMapper;

    @Operation(summary = "Get all videos", description = "Retrieves filtered videos.")
    @ApiResponse(responseCode = "200", description = "Video by filter retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No videos found")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Page<GetVideoResponse>> getFilteredVideos(
            @RequestParam(required = false) PostStatus status,
            @RequestParam(required = false) ProductionStage stage,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) String directorName,
            @RequestParam(required = false) String editorName,
            @RequestParam(required = false) String compilationName,
            Pageable pageable
    ) {
        Page<Video> filteredVideos = videoService.getFilteredVideos(
                status,
                stage,
                priority,
                directorName,
                editorName,
                compilationName,
                pageable);
        Page<GetVideoResponse> response = videoMapper.videoPageToDto(filteredVideos);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get video by id", description = "Retrieves a video by id.")
    @ApiResponse(responseCode = "200", description = "Video retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Video found")
    @GetMapping("/{id}")
    public ResponseEntity<GetVideoResponse> getVideoById(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);
        GetVideoResponse response = videoMapper.videoToDto(video);
        return ResponseEntity.ok(response);
    }



}
