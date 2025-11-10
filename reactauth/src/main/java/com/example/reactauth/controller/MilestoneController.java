/*package com.example.reactauth.controller;

import com.example.reactauth.dto.MilestoneDTO;
import com.example.reactauth.model.Milestone;
import com.example.reactauth.service.MilestoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MilestoneController {

    private final MilestoneService service;

    public MilestoneController(MilestoneService service) {
        this.service = service;
    }

    @GetMapping("/projects/{projectId}/milestones")
    public List<MilestoneDTO> getByProject(@PathVariable Long projectId) {
        return service.getByProject(projectId).stream()
                .map(m -> new MilestoneDTO(
                        m.getId(),
                        m.getTitle(),
                        m.getDescription(),
                        m.isCompleted(),
                        m.getDueDate(),
                        m.getProject().getId()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping("/projects/{projectId}/milestones")
    public MilestoneDTO add(@PathVariable Long projectId, @RequestBody MilestoneDTO dto) {
        Milestone milestone = new Milestone(dto.getTitle(), dto.getDescription(), dto.getDueDate(), dto.isCompleted(), null);
        Milestone saved = service.add(projectId, milestone);
        return new MilestoneDTO(saved.getId(), saved.getTitle(), saved.getDescription(), saved.isCompleted(), saved.getDueDate(), projectId);
    }

    @PutMapping("/milestones/{id}")
    public MilestoneDTO update(@PathVariable Long id, @RequestBody MilestoneDTO dto) {
        Milestone updated = new Milestone(dto.getTitle(), dto.getDescription(), dto.getDueDate(), dto.isCompleted(), null);
        Milestone saved = service.update(id, updated);
        return new MilestoneDTO(saved.getId(), saved.getTitle(), saved.getDescription(), saved.isCompleted(), saved.getDueDate(), saved.getProject().getId());
    }

    @DeleteMapping("/milestones/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}*/

package com.example.reactauth.controller;

import com.example.reactauth.dto.MilestoneDTO;
import com.example.reactauth.model.Milestone;
import com.example.reactauth.service.MilestoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MilestoneController {

    private final MilestoneService service;

    public MilestoneController(MilestoneService service) {
        this.service = service;
    }

    // ✅ Get all milestones for a project
    @GetMapping("/projects/{projectId}/milestones")
    public List<MilestoneDTO> getByProject(@PathVariable Long projectId) {
        return service.getByProject(projectId).stream()
                .map(m -> new MilestoneDTO(
                        m.getId(),
                        m.getTitle(),
                        m.getDescription(),
                        m.isCompleted(),
                        m.getDueDate(),
                        m.getProject().getId()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Add milestone
    @PostMapping("/projects/{projectId}/milestones")
    public MilestoneDTO add(@PathVariable Long projectId, @RequestBody MilestoneDTO dto) {
        Milestone milestone = new Milestone(
                dto.getTitle(),
                dto.getDescription(),
                dto.getDueDate(),
                dto.isCompleted(),
                null
        );
        Milestone saved = service.add(projectId, milestone);
        return new MilestoneDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted(),
                saved.getDueDate(),
                projectId
        );
    }

    // ✅ Update full milestone (Admin)
    @PutMapping("/milestones/{id}")
    public MilestoneDTO update(@PathVariable Long id, @RequestBody MilestoneDTO dto) {
        Milestone updated = new Milestone(
                dto.getTitle(),
                dto.getDescription(),
                dto.getDueDate(),
                dto.isCompleted(),
                null
        );
        Milestone saved = service.update(id, updated);
        return new MilestoneDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted(),
                saved.getDueDate(),
                saved.getProject().getId()
        );
    }

    // ✅ New: Update only milestone completion status (User)
    @PutMapping("/milestones/{id}/status")
    public MilestoneDTO updateStatus(@PathVariable Long id, @RequestBody MilestoneDTO dto) {
        Milestone existing = service.findById(id);
        existing.setCompleted(dto.isCompleted());
        Milestone saved = service.save(existing);

        return new MilestoneDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted(),
                saved.getDueDate(),
                saved.getProject().getId()
        );
    }

    // ✅ Delete milestone
    @DeleteMapping("/milestones/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

