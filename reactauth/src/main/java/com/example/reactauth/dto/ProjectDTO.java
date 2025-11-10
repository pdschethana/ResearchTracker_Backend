package com.example.reactauth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class ProjectDTO {

    private Long id;
    private String title;
    private String summary;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")   // ✅ Fix LocalDate parse from JSON
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public ProjectDTO() {}

    public ProjectDTO(Long id, String title, String summary, String status,
                      LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // ✅ Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
