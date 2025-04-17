package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Director;
import org.codeacademy.productionplanapi.exception.DirectorNotFoundException;
import org.codeacademy.productionplanapi.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public Director addDirector(Director director){
        return directorRepository.saveAndFlush(director);
    }

    public Director findDirectorById(Long id){
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("id=" + id));
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public void removeDirectorById(Long id) {
        if(!directorRepository.existsById(id)) {
            throw new DirectorNotFoundException("Director with id: " + id + " not found");
        }
        directorRepository.deleteById(id);
    }
}
