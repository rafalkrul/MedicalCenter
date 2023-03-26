package com.example.medicalcenter.repository;


import com.example.medicalcenter.model.ResearchProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResearchProjectRepository extends JpaRepository <ResearchProject, UUID> {
}
