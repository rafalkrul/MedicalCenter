package com.example.medicalcenter.repository;


import com.example.medicalcenter.model.ResearchOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResearchOrderRepository extends JpaRepository<ResearchOrder, UUID> {
}
