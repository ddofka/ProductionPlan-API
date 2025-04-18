package org.codeacademy.productionplanapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.create.CreateEditorRequest;
import org.codeacademy.productionplanapi.dto.get.GetEditorResponse;
import org.codeacademy.productionplanapi.entity.Editor;
import org.codeacademy.productionplanapi.mapper.EditorMapper;
import org.codeacademy.productionplanapi.service.EditorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/editors")
@RequiredArgsConstructor
@RestController
public class EditorController {

    private final EditorService editorService;
    private final EditorMapper editorMapper;

    @Operation(summary = "Get all editors", description = "Retrieves a list of all editors.")
    @ApiResponse(responseCode = "200", description = "List of editors retrieved successfully")
    @ApiResponse(responseCode = "204", description = "No editors found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<GetEditorResponse>> getAllEditors(){
        List<GetEditorResponse> editors = editorMapper.editorListToDto(editorService.getAllEditors());
        if (editors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(editors);
    }

    @Operation(summary = "Create editor", description = "Creates editors from request.")
    @ApiResponse(responseCode = "201", description = "Editor created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<GetEditorResponse> createEditor(@RequestBody CreateEditorRequest request){
        Editor editor = editorService.addEditor(editorMapper.dtoToEditor(request));
        GetEditorResponse response = editorMapper.editorToDto(editor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete editor", description = "Deletes the editor by ID.")
    @ApiResponse(responseCode = "204", description = "Editor deleted successfully")
    @ApiResponse(responseCode = "404", description = "Editor not found")
    @ApiResponse(responseCode = "401", description = "Full authentication is required to access this resource")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditorById(@PathVariable Long id) {
        editorService.removeEditorById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
