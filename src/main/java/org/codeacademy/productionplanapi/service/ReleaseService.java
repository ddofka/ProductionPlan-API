package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeacademy.productionplanapi.dto.create.CreateReleaseRequest;
import org.codeacademy.productionplanapi.entity.Release;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.exception.TestInformationNotFoundException;
import org.codeacademy.productionplanapi.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    public List<Release> addReleases(List<Release> releases){
        return releaseRepository.saveAllAndFlush(releases);
    }

    public List<Release> getReleasesByVideoId(Long id){
        List<Release> maybeReleases = releaseRepository.findAllByVideoId(id);
        if (maybeReleases.isEmpty()) {
            throw new TestInformationNotFoundException("Video id=" + id);
        }
        return maybeReleases;
    }

    public void updateReleases(Video videoFromDb, List<CreateReleaseRequest> releases) {
        log.info("Entered release service to update release information");
        log.info("creating updated release list");
        List<Release> updated = releases.stream()
                .peek(t->log.info("release info fields: {}",t))
                .map(r->{
                    Release newRelease = new Release();
                    newRelease.setReleaseDateTime(r.releaseDateTime());
                    newRelease.setPart(r.part());
                    newRelease.setVideo(videoFromDb);
                    return newRelease;
                }).toList();
        log.info("clearing release information");
        videoFromDb.getReleases().clear();
        log.info("adding updated release information");
        videoFromDb.getReleases().addAll(updated);
        log.info("release updated");
    }

}
