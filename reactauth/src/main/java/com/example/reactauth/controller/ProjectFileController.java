/*package com.example.reactauth.controller;

import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectFileController {

    private final ProjectFileService fileService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileController(ProjectFileService fileService) {
        this.fileService = fileService;
    }

    // ✅ Upload file
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<ProjectFile> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy) throws IOException {

        ProjectFile savedFile = fileService.uploadFile(projectId, file, uploadedBy);
        return ResponseEntity.ok(savedFile);
    }

    // ✅ List all files for a project
    @GetMapping("/{projectId}/files")
    public ResponseEntity<List<ProjectFile>> getProjectFiles(@PathVariable Long projectId) {
        return ResponseEntity.ok(fileService.getFilesByProject(projectId));
    }

    // ✅ Download file
    @GetMapping("/files/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
    }
}*/

/*package com.example.reactauth.controller;

import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectFileController {

    private final ProjectFileService fileService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileController(ProjectFileService fileService) {
        this.fileService = fileService;
    }

    // ✅ Upload a file for a project
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<ProjectFile> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy
    ) throws IOException {
        ProjectFile savedFile = fileService.uploadFile(projectId, file, uploadedBy);
        return ResponseEntity.ok(savedFile);
    }

    // ✅ Get all files of a project
    @GetMapping("/{projectId}/files")
    public ResponseEntity<List<ProjectFile>> getProjectFiles(@PathVariable Long projectId) {
        return ResponseEntity.ok(fileService.getFilesByProject(projectId));
    }

    // ✅ Download a specific file
    @GetMapping("/files/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}*/

/*package com.example.reactauth.controller;

import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectFileController {

    private final ProjectFileService fileService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileController(ProjectFileService fileService) {
        this.fileService = fileService;
    }

    // ✅ Upload a file
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<ProjectFile> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy
    ) throws IOException {
        ProjectFile savedFile = fileService.uploadFile(projectId, file, uploadedBy);

        // Map filePath to frontend-accessible URL
        savedFile.setFilePath("/api/projects/files/download/" + savedFile.getFileName());

        return ResponseEntity.ok(savedFile);
    }

    // ✅ Get all files of a project
    @GetMapping("/{projectId}/files")
    public ResponseEntity<List<ProjectFile>> getProjectFiles(@PathVariable Long projectId) {
        List<ProjectFile> files = fileService.getFilesByProject(projectId);

        // Map each filePath to frontend-accessible download URL
        files.forEach(f -> f.setFilePath("/api/projects/files/download/" + f.getFileName()));

        return ResponseEntity.ok(files);
    }

    // ✅ Download a specific file
    @GetMapping("/files/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}*/

/*package com.example.reactauth.controller;

import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectFileController {

    private final ProjectFileService fileService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileController(ProjectFileService fileService) {
        this.fileService = fileService;
    }

    // ✅ Upload a file for a project
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<ProjectFile> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy
    ) throws IOException {
        ProjectFile savedFile = fileService.uploadFile(projectId, file, uploadedBy);
        return ResponseEntity.ok(savedFile);
    }

    // ✅ Get all files of a project with proper fileUrl
    @GetMapping("/{projectId}/files")
    public ResponseEntity<List<ProjectFileResponse>> getProjectFiles(@PathVariable Long projectId) {
        List<ProjectFile> files = fileService.getFilesByProject(projectId);
        List<ProjectFileResponse> response = files.stream().map(f -> new ProjectFileResponse(
                f.getId(),
                f.getFileName(),
                f.getUploadedBy(),
                "/api/projects/files/download/" + f.getFileName()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // ✅ Download a specific file
    @GetMapping("/files/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    // ✅ DTO for frontend
    public static class ProjectFileResponse {
        private Long id;
        private String fileName;
        private String uploadedBy;
        private String fileUrl;

        public ProjectFileResponse(Long id, String fileName, String uploadedBy, String fileUrl) {
            this.id = id;
            this.fileName = fileName;
            this.uploadedBy = uploadedBy;
            this.fileUrl = fileUrl;
        }

        public Long getId() { return id; }
        public String getFileName() { return fileName; }
        public String getUploadedBy() { return uploadedBy; }
        public String getFileUrl() { return fileUrl; }

        public void setId(Long id) { this.id = id; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
        public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    }
}*/

/*package com.example.reactauth.controller;

import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.service.ProjectFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectFileController {

    private final ProjectFileService fileService;
    private final String uploadDir = "uploads"; // Make sure to set in application.properties if needed

    public ProjectFileController(ProjectFileService fileService) {
        this.fileService = fileService;
    }

    // Upload file
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<ProjectFile> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy
    ) throws IOException {
        ProjectFile savedFile = fileService.uploadFile(projectId, file, uploadedBy);
        return ResponseEntity.ok(savedFile);
    }

    // Get all project files
    @GetMapping("/{projectId}/files")
    public ResponseEntity<List<ProjectFileResponse>> getProjectFiles(@PathVariable Long projectId) {
        List<ProjectFile> files = fileService.getFilesByProject(projectId);

        List<ProjectFileResponse> response = files.stream()
                .map(f -> new ProjectFileResponse(
                        f.getId(),
                        f.getFileName(),
                        f.getUploadedBy(),
                        f.getUploadedAt(),
                        "/api/projects/files/download/" + f.getFileName()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Download file
    @GetMapping("/files/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    // DTO
    public static class ProjectFileResponse {
        private Long id;
        private String fileName;
        private String uploadedBy;
        private LocalDateTime uploadedAt;
        private String fileUrl;

        public ProjectFileResponse(Long id, String fileName, String uploadedBy, LocalDateTime uploadedAt, String fileUrl) {
            this.id = id;
            this.fileName = fileName;
            this.uploadedBy = uploadedBy;
            this.uploadedAt = uploadedAt;
            this.fileUrl = fileUrl;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public String getFileName() { return fileName; }
        public String getUploadedBy() { return uploadedBy; }
        public LocalDateTime getUploadedAt() { return uploadedAt; }
        public String getFileUrl() { return fileUrl; }

        public void setId(Long id) { this.id = id; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
        public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
        public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    }
}*/

package com.example.reactauth.controller;

import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.service.ProjectFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectFileController {

    private final ProjectFileService fileService;

    public ProjectFileController(ProjectFileService fileService) {
        this.fileService = fileService;
    }

    // ✅ Upload file
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<ProjectFileResponse> uploadFile(
            @PathVariable Long projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy
    ) {
        try {
            ProjectFile savedFile = fileService.uploadFile(projectId, file, uploadedBy);

            ProjectFileResponse response = new ProjectFileResponse(
                    savedFile.getId(),
                    savedFile.getFileName(),
                    savedFile.getUploadedBy(),
                    savedFile.getUploadedAt(),
                    savedFile.getFileUrl()
            );

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // ✅ Get all project files
    @GetMapping("/{projectId}/files")
    public ResponseEntity<List<ProjectFileResponse>> getProjectFiles(@PathVariable Long projectId) {
        List<ProjectFile> files = fileService.getFilesByProject(projectId);

        List<ProjectFileResponse> response = files.stream()
                .map(f -> new ProjectFileResponse(
                        f.getId(),
                        f.getFileName(),
                        f.getUploadedBy(),
                        f.getUploadedAt(),
                        f.getFileUrl() // Direct URL to file
                )).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // ✅ Delete file (Admin only)
    @DeleteMapping("/files/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            fileService.deleteFile(id);
            return ResponseEntity.ok("File deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to delete file");
        }
    }

    // DTO
    public static class ProjectFileResponse {
        private Long id;
        private String fileName;
        private String uploadedBy;
        private LocalDateTime uploadedAt;
        private String fileUrl;

        public ProjectFileResponse(Long id, String fileName, String uploadedBy, LocalDateTime uploadedAt, String fileUrl) {
            this.id = id;
            this.fileName = fileName;
            this.uploadedBy = uploadedBy;
            this.uploadedAt = uploadedAt;
            this.fileUrl = fileUrl;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public String getFileName() { return fileName; }
        public String getUploadedBy() { return uploadedBy; }
        public LocalDateTime getUploadedAt() { return uploadedAt; }
        public String getFileUrl() { return fileUrl; }

        public void setId(Long id) { this.id = id; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
        public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
        public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    }
}




