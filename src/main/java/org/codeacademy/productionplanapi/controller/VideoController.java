package org.codeacademy.productionplanapi.controller;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.get.GetVideoResponse;
import org.codeacademy.productionplanapi.mapper.VideoMapper;
import org.codeacademy.productionplanapi.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final VideoMapper videoMapper;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<GetVideoResponse>> getAllVideos() {
        List<GetVideoResponse> videos = videoMapper.videoListToDto(videoService.getAllVideos());
        if (videos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(videos);
    }



}
