package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Director;
import org.codeacademy.productionplanapi.entity.Editor;
import org.codeacademy.productionplanapi.exception.EditorNotFoundException;
import org.codeacademy.productionplanapi.repository.EditorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorService {

    private final EditorRepository editorRepository;

    public Editor addEditor(Editor editor){
        return editorRepository.saveAndFlush(editor);
    }

    public Editor findEditorById(Long id){
        return editorRepository.findById(id)
                .orElseThrow(() -> new EditorNotFoundException("id=" + id));
    }

    public List<Editor> getAllEditors() {
        return editorRepository.findAll();
    }

    public void removeEditorById(Long id) {
        if(!editorRepository.existsById(id)) {
            throw new EditorNotFoundException("Editor with id: " + id + " not found");
        }
        editorRepository.deleteById(id);
    }

}
