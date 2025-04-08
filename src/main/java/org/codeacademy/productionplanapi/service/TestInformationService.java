package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.repository.TestInformationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestInformationService {

    private final TestInformationRepository testInformationRepository;

}
