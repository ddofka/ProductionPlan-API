package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Video;

import org.codeacademy.productionplanapi.exception.VideoNotFoundException;
import org.codeacademy.productionplanapi.repository.VideoRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public Video addVideo(Video video) {
        return videoRepository.saveAndFlush(video);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException("id=" + id));
    }

    public void removeVideoById(Long id) {
        videoRepository.deleteById(id);
    }

}
