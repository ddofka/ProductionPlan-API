package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.TestInformation;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.exception.TestInformationNotFoundException;
import org.codeacademy.productionplanapi.repository.TestInformationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestInformationService {

    private final TestInformationRepository testInformationRepository;

    public TestInformation addTestInformation(TestInformation testInformation) {
        return testInformationRepository.saveAndFlush(testInformation);
    }

    public TestInformation getTestInformationById(Long id) {
        return testInformationRepository.findById(id)
                .orElseThrow(() -> new TestInformationNotFoundException("id="+id));
    }

    public void addTestInformation(Video video){
        TestInformation testInformation = new TestInformation();
        testInformation.setVideo(video);
        testInformation.setVersion("V1");
        testInformation.setVersion("V2");
        testInformation.setTimeName("3s");
        testInformation.setTimeValue(45.33);
        addTestInformation(testInformation);
    }

}
