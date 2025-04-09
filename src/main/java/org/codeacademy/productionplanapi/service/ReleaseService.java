package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Release;
import org.codeacademy.productionplanapi.exception.ReleaseNotFoundException;
import org.codeacademy.productionplanapi.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

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

}
