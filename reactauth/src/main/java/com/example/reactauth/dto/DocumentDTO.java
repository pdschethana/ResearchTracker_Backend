package com.example.reactauth.dto;

import java.time.LocalDateTime;

public class DocumentDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private LocalDateTime uploadedAt;
    private String uploadedBy;
    private Long projectId;

    public DocumentDTO() {}

    public DocumentDTO(Long id, String fileName, String fileType, String fileUrl, LocalDateTime uploadedAt, String uploadedBy, Long projectId) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
        this.uploadedBy = uploadedBy;
        this.projectId = projectId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public String getFileUrl() { return fileUrl; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public String getUploadedBy() { return uploadedBy; }
    public Long getProjectId() { return projectId; }
}
