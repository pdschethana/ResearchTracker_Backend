/*package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.repository.ProjectFileRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ProjectFileService {

    private final ProjectFileRepository fileRepo;
    private final ProjectRepository projectRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileService(ProjectFileRepository fileRepo, ProjectRepository projectRepo) {
        this.fileRepo = fileRepo;
        this.projectRepo = projectRepo;
    }

    public ProjectFile uploadFile(Long projectId, MultipartFile file, String uploadedBy) throws IOException {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        ProjectFile projectFile = new ProjectFile();
        projectFile.setFileName(fileName);
        projectFile.setFilePath(filePath.toString());
        projectFile.setUploadedBy(uploadedBy);
        projectFile.setProject(project);

        return fileRepo.save(projectFile);
    }

    public List<ProjectFile> getFilesByProject(Long projectId) {
        return fileRepo.findByProjectId(projectId);
    }
}*/

/*package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.repository.ProjectFileRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ProjectFileService {

    private final ProjectFileRepository fileRepo;
    private final ProjectRepository projectRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileService(ProjectFileRepository fileRepo, ProjectRepository projectRepo) {
        this.fileRepo = fileRepo;
        this.projectRepo = projectRepo;
    }

    // ✅ Upload file and link it to a project
    public ProjectFile uploadFile(Long projectId, MultipartFile file, String uploadedBy) throws IOException {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // ✅ Create the uploads folder if it does not exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file with a unique name to avoid collisions
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save file info to database
        ProjectFile projectFile = new ProjectFile();
        projectFile.setFileName(fileName);
        projectFile.setFilePath(filePath.toString());
        projectFile.setUploadedBy(uploadedBy);
        projectFile.setProject(project);

        return fileRepo.save(projectFile);
    }

    // ✅ Fetch all files for a given project
    public List<ProjectFile> getFilesByProject(Long projectId) {
        return fileRepo.findByProjectId(projectId);
    }
}*/

/*package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.repository.ProjectFileRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ProjectFileService {

    private final ProjectFileRepository fileRepo;
    private final ProjectRepository projectRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileService(ProjectFileRepository fileRepo, ProjectRepository projectRepo) {
        this.fileRepo = fileRepo;
        this.projectRepo = projectRepo;
    }

    // ✅ Upload file and save to DB
    public ProjectFile uploadFile(Long projectId, MultipartFile file, String uploadedBy) throws IOException {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        ProjectFile projectFile = new ProjectFile();
        projectFile.setFileName(fileName);
        projectFile.setFilePath(filePath.toString()); // Will map to URL in controller
        projectFile.setUploadedBy(uploadedBy);
        projectFile.setProject(project);

        return fileRepo.save(projectFile);
    }

    public List<ProjectFile> getFilesByProject(Long projectId) {
        return fileRepo.findByProjectId(projectId);
    }
}*/

package com.example.reactauth.service;

import com.example.reactauth.model.Project;
import com.example.reactauth.model.ProjectFile;
import com.example.reactauth.repository.ProjectFileRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectFileService {

    private final ProjectFileRepository fileRepo;
    private final ProjectRepository projectRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectFileService(ProjectFileRepository fileRepo, ProjectRepository projectRepo) {
        this.fileRepo = fileRepo;
        this.projectRepo = projectRepo;
    }

    // Upload a file and link to a project
    public ProjectFile uploadFile(Long projectId, MultipartFile file, String uploadedBy) throws IOException {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        ProjectFile projectFile = new ProjectFile();
        projectFile.setFileName(fileName);
        projectFile.setFilePath(filePath.toString());
        projectFile.setUploadedBy(uploadedBy);
        projectFile.setUploadedAt(LocalDateTime.now()); // ✅ Timestamp
        projectFile.setProject(project);

        return fileRepo.save(projectFile);
    }

    public List<ProjectFile> getFilesByProject(Long projectId) {
        return fileRepo.findByProjectId(projectId);
    }
}



