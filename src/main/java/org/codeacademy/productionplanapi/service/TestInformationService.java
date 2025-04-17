package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeacademy.productionplanapi.dto.create.CreateTestInformationRequest;
import org.codeacademy.productionplanapi.entity.TestInformation;
import org.codeacademy.productionplanapi.entity.Video;
import org.codeacademy.productionplanapi.exception.TestInformationNotFoundException;
import org.codeacademy.productionplanapi.repository.TestInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestInformationService {

    private final TestInformationRepository testInformationRepository;

    public List<TestInformation> getTestInformationByVideoId(Long id) {
        List<TestInformation> maybeTests = testInformationRepository.findAllByVideoId(id);
        if (maybeTests.isEmpty()) {
            throw new TestInformationNotFoundException("Video id=" + id);
        }
        return maybeTests;
    }

    public void upodateTestInfos(Video videoFromDb, List<CreateTestInformationRequest> tests) {
        log.info("Entered test service to update test information");
        log.info("creating updated test info list");
        List<TestInformation> updatedTestInfo = tests.stream()
                .peek(t->log.info("test info fields: {}",t))
                .map(t->{
                    TestInformation testInformation = new TestInformation();
                    testInformation.setRetentionValue(t.retentionValue());
                    testInformation.setVersion(t.version());
                    testInformation.setVideo(videoFromDb);
                    testInformation.setRetentionTime(t.retentionTime());
                    return testInformation;
                }).toList();
        log.info("clearing tests information");
        videoFromDb.getTests().clear();
        log.info("adding updated tests information");
        videoFromDb.getTests().addAll(updatedTestInfo);
        log.info("tests updated");
    }
}
