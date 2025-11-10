/*package com.example.reactauth.repository;

import com.example.reactauth.model.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
    List<ProjectFile> findByProjectId(Long projectId);
}*/

/*package com.example.reactauth.repository;

import com.example.reactauth.model.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {

    // ✅ Fetch all files linked to a specific project
    List<ProjectFile> findByProjectId(Long projectId);
}*/

package com.example.reactauth.repository;

import com.example.reactauth.model.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
    List<ProjectFile> findByProjectId(Long projectId);
}


