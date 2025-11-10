/*package com.example.reactauth.controller;

import com.example.reactauth.dto.DocumentDTO;
import com.example.reactauth.model.Document;
import com.example.reactauth.service.DocumentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    // ✅ View all documents for a project
    @GetMapping("/projects/{projectId}/documents")
    public List<DocumentDTO> getByProject(@PathVariable Long projectId) {
        return service.getByProject(projectId).stream()
                .map(d -> new DocumentDTO(
                        d.getId(),
                        d.getFileName(),
                        d.getFileType(),
                        d.getFileUrl(),
                        d.getUploadedAt(),
                        d.getUploadedBy(),
                        d.getProject().getId()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Upload document (Allowed for ADMIN and MEMBER)
    @PostMapping("/projects/{projectId}/documents")
    public DocumentDTO upload(@PathVariable Long projectId, @RequestParam("file") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // who uploaded it

        String fakeUrl = "/uploads/" + file.getOriginalFilename();
        Document doc = service.upload(projectId, file.getOriginalFilename(), file.getContentType(), fakeUrl, username);

        return new DocumentDTO(
                doc.getId(),
                doc.getFileName(),
                doc.getFileType(),
                doc.getFileUrl(),
                doc.getUploadedAt(),
                username,
                projectId
        );
    }

    // ✅ Delete document (Admin only)
    @DeleteMapping("/documents/{id}")
    public String delete(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();

        if (!role.equals("ROLE_ADMIN")) {
            throw new RuntimeException("❌ Access Denied: Only admins can delete documents.");
        }

        service.delete(id);
        return "Document deleted successfully";
    }
}*/

package com.example.reactauth.controller;

import com.example.reactauth.dto.DocumentDTO;
import com.example.reactauth.model.Document;
import com.example.reactauth.service.DocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService service;

    // ✅ Inject the upload directory path from application.properties
    @Value("${file.upload-dir}")
    private String uploadDir;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    // ✅ View all documents for a project
    @GetMapping("/projects/{projectId}/documents")
    public List<DocumentDTO> getByProject(@PathVariable Long projectId) {
        return service.getByProject(projectId).stream()
                .map(d -> new DocumentDTO(
                        d.getId(),
                        d.getFileName(),
                        d.getFileType(),
                        d.getFileUrl(),
                        d.getUploadedAt(),
                        d.getUploadedBy(),
                        d.getProject().getId()
                ))
                .collect(Collectors.toList());
    }

    // ✅ Upload a document (Allowed for ADMIN and MEMBER)
    @PostMapping("/projects/{projectId}/documents")
    public DocumentDTO upload(@PathVariable Long projectId, @RequestParam("file") MultipartFile file) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // who uploaded it

        // ✅ Ensure upload directory exists
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // ✅ Save the file to the uploads folder
        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        // ✅ Create file URL for access (served by Spring Boot)
        String fileUrl = "/uploads/" + file.getOriginalFilename();

        // ✅ Save document details in DB
        Document doc = service.upload(projectId, file.getOriginalFilename(), file.getContentType(), fileUrl, username);

        return new DocumentDTO(
                doc.getId(),
                doc.getFileName(),
                doc.getFileType(),
                doc.getFileUrl(),
                doc.getUploadedAt(),
                username,
                projectId
        );
    }

    // ✅ Delete document (Admin only)
    @DeleteMapping("/documents/{id}")
    public String delete(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().iterator().next().getAuthority();

        if (!role.equals("ROLE_ADMIN")) {
            throw new RuntimeException("❌ Access Denied: Only admins can delete documents.");
        }

        service.delete(id);
        return "Document deleted successfully";
    }
}

