package com.example.medicalcenter.repository;


import com.example.medicalcenter.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {

    Agreement findAgreementByPatientIdAndAndResearchProjectId(UUID patientId, UUID researchProjectId);

    List<Agreement> findAllByPatientId(UUID patientId);

    List<Agreement> findAllByResearchProjectId(UUID projectId);

}
