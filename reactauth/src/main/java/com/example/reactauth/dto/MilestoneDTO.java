package com.example.reactauth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class MilestoneDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private Long projectId;

    public MilestoneDTO() {}

    public MilestoneDTO(Long id, String title, String description, boolean completed, LocalDate dueDate, Long projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.projectId = projectId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
}
