/*package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }

    public List<Project> getAll() {
        return repo.findAll();
    }

    public Project save(Project project) {
        return repo.save(project);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}*/
/*package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }

    public List<Project> getAll() {
        return repo.findAll();
    }

    public Project save(Project project) {
        return repo.save(project);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ✅ Get project by ID (for update)
    public Project getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}*/


package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.model.User;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }

    // Get all projects
    public List<Project> getAll() {
        return repo.findAll();
    }

    // Save or update project
    public Project save(Project project) {
        return repo.save(project);
    }

    // Delete project
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Get project by ID (for update)
    public Project getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // ✅ Get all projects assigned to a specific user
    public List<Project> getByAssignedUser(User user) {
        return repo.findAll().stream()
                .filter(p -> p.getAssignedUsers() != null && p.getAssignedUsers().contains(user))
                .collect(Collectors.toList());
    }
}







