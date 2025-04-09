package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.TestInformation;
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

}
