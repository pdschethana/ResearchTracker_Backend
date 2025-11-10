package com.example.reactauth.repository;

import com.example.reactauth.model.Document;
import com.example.reactauth.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByProject(Project project);
}
