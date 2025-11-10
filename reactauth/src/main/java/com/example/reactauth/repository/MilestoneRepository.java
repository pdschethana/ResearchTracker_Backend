package com.example.reactauth.repository;

import com.example.reactauth.model.Milestone;
import com.example.reactauth.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByProject(Project project);
}
