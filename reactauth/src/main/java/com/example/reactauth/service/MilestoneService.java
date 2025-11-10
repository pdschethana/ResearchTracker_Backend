/*package com.example.reactauth.service;

import com.example.reactauth.model.Milestone;
import com.example.reactauth.model.Project;
import com.example.reactauth.repository.MilestoneRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepo;
    private final ProjectRepository projectRepo;

    public MilestoneService(MilestoneRepository milestoneRepo, ProjectRepository projectRepo) {
        this.milestoneRepo = milestoneRepo;
        this.projectRepo = projectRepo;
    }

    public List<Milestone> getByProject(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return milestoneRepo.findByProject(project);
    }

    public Milestone add(Long projectId, Milestone milestone) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        milestone.setProject(project);
        return milestoneRepo.save(milestone);
    }

    public Milestone update(Long id, Milestone updated) {
        Milestone existing = milestoneRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDueDate(updated.getDueDate());
        existing.setCompleted(updated.isCompleted());
        return milestoneRepo.save(existing);
    }

    public void delete(Long id) {
        milestoneRepo.deleteById(id);
    }
}*/

package com.example.reactauth.service;

import com.example.reactauth.model.Milestone;
import com.example.reactauth.model.Project;
import com.example.reactauth.repository.MilestoneRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepo;
    private final ProjectRepository projectRepo;

    public MilestoneService(MilestoneRepository milestoneRepo, ProjectRepository projectRepo) {
        this.milestoneRepo = milestoneRepo;
        this.projectRepo = projectRepo;
    }

    public List<Milestone> getByProject(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return milestoneRepo.findByProject(project);
    }

    public Milestone add(Long projectId, Milestone milestone) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        milestone.setProject(project);
        return milestoneRepo.save(milestone);
    }

    public Milestone update(Long id, Milestone updated) {
        Milestone existing = milestoneRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDueDate(updated.getDueDate());
        existing.setCompleted(updated.isCompleted());
        return milestoneRepo.save(existing);
    }

    // ✅ New: Find milestone by ID
    public Milestone findById(Long id) {
        return milestoneRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
    }

    // ✅ New: Save milestone directly (for updating status only)
    public Milestone save(Milestone milestone) {
        return milestoneRepo.save(milestone);
    }

    public void delete(Long id) {
        milestoneRepo.deleteById(id);
    }
}

