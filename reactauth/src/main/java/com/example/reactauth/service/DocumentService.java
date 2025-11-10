package com.example.reactauth.service;

import com.example.reactauth.model.Document;
import com.example.reactauth.model.Project;
import com.example.reactauth.repository.DocumentRepository;
import com.example.reactauth.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepo;
    private final ProjectRepository projectRepo;

    public DocumentService(DocumentRepository documentRepo, ProjectRepository projectRepo) {
        this.documentRepo = documentRepo;
        this.projectRepo = projectRepo;
    }

    public List<Document> getByProject(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return documentRepo.findByProject(project);
    }

    public Document upload(Long projectId, String fileName, String fileType, String fileUrl, String uploadedBy) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Document doc = new Document(fileName, fileType, fileUrl, LocalDateTime.now(), uploadedBy, project);
        return documentRepo.save(doc);
    }

    public void delete(Long id) {
        documentRepo.deleteById(id);
    }
}
