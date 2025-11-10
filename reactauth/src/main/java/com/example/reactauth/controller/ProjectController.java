package com.example.reactauth.controller;

import com.example.reactauth.dto.ProjectDTO;
import com.example.reactauth.model.Project;
import com.example.reactauth.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")

public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    // ✅ Get all projects
    @GetMapping
    public List<ProjectDTO> getAll() {
        return service.getAll().stream()
                .map(p -> new ProjectDTO(
                        p.getId(),
                        p.getTitle(),
                        p.getSummary(),
                        p.getStatus(),
                        p.getStartDate(),
                        p.getEndDate()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Add new project
    @PostMapping
    public ProjectDTO create(@RequestBody ProjectDTO dto) {
        Project project = new Project(
                dto.getTitle(),
                dto.getSummary(),
                dto.getStatus(),
                dto.getStartDate(),
                dto.getEndDate()
        );
        Project saved = service.save(project);
        return new ProjectDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getSummary(),
                saved.getStatus(),
                saved.getStartDate(),
                saved.getEndDate()
        );
    }

    // ✅ Delete a project
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
