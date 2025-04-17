package org.codeacademy.productionplanapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.dto.create.CreateDirectorRequest;
import org.codeacademy.productionplanapi.dto.get.GetDirectorResponse;
import org.codeacademy.productionplanapi.entity.Director;
import org.codeacademy.productionplanapi.mapper.DirectorMapper;
import org.codeacademy.productionplanapi.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/directors")
@RequiredArgsConstructor
@RestController
public class DirectorController {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;

    @Operation(summary = "Get all directors", description = "Retrieves a list of all directors.")
    @ApiResponse(responseCode = "200", description = "List of directors retrieved successfully")
    @ApiResponse(responseCode = "204", description = "No directors found")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<GetDirectorResponse>> getAllDirectors(){
        List<GetDirectorResponse> directors = directorMapper.directorsListToDto(directorService.getAllDirectors());
        if (directors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(directors);
    }

    @Operation(summary = "Create director", description = "Creates director from request.")
    @ApiResponse(responseCode = "201", description = "Director created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<GetDirectorResponse> createDirector(@RequestBody CreateDirectorRequest request){
        Director director = directorService.addDirector(directorMapper.dtoToDirector(request));
        GetDirectorResponse response = directorMapper.directorToDto(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete director", description = "Deletes the director by ID.")
    @ApiResponse(responseCode = "204", description = "Director deleted successfully")
    @ApiResponse(responseCode = "404", description = "Director not found")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirectorById(@PathVariable Long id) {
        directorService.removeDirectorById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
