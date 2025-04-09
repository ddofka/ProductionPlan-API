package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Director;
import org.codeacademy.productionplanapi.exception.DirectorNotFoundException;
import org.codeacademy.productionplanapi.repository.DirectorRepository;
import org.springframework.stereotype.Service;

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

    public void addTestDirector(){
        Director director = new Director();
        director.setName("Sebastian");
        director.setCountry("Columbia");
        addDirector(director);

        Director directorTwo = new Director();
        directorTwo.setName("Paulius");
        directorTwo.setCountry("Lithuania");
        addDirector(directorTwo);

        Director directorThree = new Director();
        directorThree.setName("Sandji");
        directorThree.setCountry("India");
        addDirector(directorThree);
    }

}
