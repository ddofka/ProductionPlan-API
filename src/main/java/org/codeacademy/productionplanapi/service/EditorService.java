package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.repository.EditorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditorService {

    private final EditorRepository editorRepository;

}
