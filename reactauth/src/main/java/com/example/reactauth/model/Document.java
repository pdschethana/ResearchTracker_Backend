package com.example.reactauth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private String fileUrl;
    private LocalDateTime uploadedAt;
    private String uploadedBy; // 🆕 track who uploaded

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Document() {}

    public Document(String fileName, String fileType, String fileUrl, LocalDateTime uploadedAt, String uploadedBy, Project project) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
        this.uploadedBy = uploadedBy;
        this.project = project;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}

