package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Release;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.exception.ReleaseNotFoundException;
import org.codeacademy.productionplanapi.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    public Release addRelease(Release release){
        return releaseRepository.saveAndFlush(release);
    }

    public Release getReleaseById(Long id){
        return releaseRepository.findById(id)
                .orElseThrow(() -> new ReleaseNotFoundException("id="+id));
    }

    public void addTestRelease(Video video){
        Release release = new Release();
        release.setVideo(video);
        release.setSequence(1);
        release.setReleaseDateTime(LocalDateTime.of(2025, 4, 9, 11, 0));
        addRelease(release);
    }

}
