/*package com.example.reactauth.controller;

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
}*/

/*package com.example.reactauth.controller;

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

    // ✅ Update existing project
    @PutMapping("/{id}")
    public ProjectDTO update(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        Project existing = service.getById(id);
        existing.setTitle(dto.getTitle());
        existing.setSummary(dto.getSummary());
        existing.setStatus(dto.getStatus());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        Project updated = service.save(existing);
        return new ProjectDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getSummary(),
                updated.getStatus(),
                updated.getStartDate(),
                updated.getEndDate()
        );
    }

    // ✅ Delete a project
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}*/

package com.example.reactauth.controller;

import com.example.reactauth.dto.ProjectDTO;
import com.example.reactauth.model.Project;
import com.example.reactauth.model.User;
import com.example.reactauth.service.ProjectService;
import com.example.reactauth.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    // Get all projects
    @GetMapping
    public List<ProjectDTO> getAll() {
        return projectService.getAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get projects assigned to user
    @GetMapping("/user/{username}")
    public List<ProjectDTO> getByUser(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return projectService.getByAssignedUser(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Create project
    @PostMapping
    public ProjectDTO create(@RequestBody ProjectDTO dto) {
        Project project = new Project(dto.getTitle(), dto.getSummary(), dto.getStatus(), dto.getStartDate(), dto.getEndDate());

        if (dto.getAssignedUsers() != null) {
            Set<User> users = dto.getAssignedUsers().stream()
                    .map(userService::getByUsername)
                    .collect(Collectors.toSet());
            project.setAssignedUsers(users);
        }

        Project saved = projectService.save(project);
        return convertToDTO(saved);
    }

    // Update project
    @PutMapping("/{id}")
    public ProjectDTO update(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        Project existing = projectService.getById(id);
        existing.setTitle(dto.getTitle());
        existing.setSummary(dto.getSummary());
        existing.setStatus(dto.getStatus());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());

        if (dto.getAssignedUsers() != null) {
            Set<User> users = dto.getAssignedUsers().stream()
                    .map(userService::getByUsername)
                    .collect(Collectors.toSet());
            existing.setAssignedUsers(users);
        }

        Project updated = projectService.save(existing);
        return convertToDTO(updated);
    }

    // Delete project
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    private ProjectDTO convertToDTO(Project p) {
        List<String> assigned = p.getAssignedUsers().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
        return new ProjectDTO(p.getId(), p.getTitle(), p.getSummary(), p.getStatus(),
                p.getStartDate(), p.getEndDate(), assigned);
    }
}


