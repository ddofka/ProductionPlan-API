package org.codeacademy.productionplanapi.service;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.entity.Editor;
import org.codeacademy.productionplanapi.exception.EditorNotFoundException;
import org.codeacademy.productionplanapi.repository.EditorRepository;
import org.springframework.stereotype.Service;

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

    public void addTestEditor(){
        Editor editor = new Editor();
        editor.setName("Dovydas");
        addEditor(editor);

        Editor editorOne = new Editor();
        editorOne.setName("Linas");
        editorOne.setFreelancer(true);
        addEditor(editorOne);
    }

}
