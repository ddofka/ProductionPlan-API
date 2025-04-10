package org.codeacademy.productionplanapi.mapper;

import org.codeacademy.productionplanapi.dto.create.CreateEditorRequest;
import org.codeacademy.productionplanapi.dto.get.GetEditorResponse;
import org.codeacademy.productionplanapi.entity.Editor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditorMapper {

    GetEditorResponse editorToDto(Editor editor);

    List<GetEditorResponse> editorListToDto(List<Editor> editors);

    Editor dtoToEditor(CreateEditorRequest request);

}
