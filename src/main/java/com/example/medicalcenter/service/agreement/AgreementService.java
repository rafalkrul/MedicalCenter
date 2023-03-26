package com.example.medicalcenter.service.agreement;


import com.example.medicalcenter.DTO.agreement.AgreementGetDTO;
import com.example.medicalcenter.DTO.agreement.AgreementPostDTO;
import com.example.medicalcenter.model.Agreement;
import com.example.medicalcenter.model.Patient;
import com.example.medicalcenter.model.ResearchProject;
import com.example.medicalcenter.repository.AgreementRepository;
import com.example.medicalcenter.repository.PatientRepository;
import com.example.medicalcenter.repository.ResearchProjectRepository;
import com.example.medicalcenter.service.researchProject.ResearchProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgreementService {


    private final AgreementRepository agreementRepository;
    private final ResearchProjectService researchProjectService;
    private final PatientRepository patientRepository;
    private final ModelMapper mapper;
    private final ResearchProjectRepository researchProjectRepository;



    public UUID createAgreement(AgreementPostDTO agreementPostDTO){

        Agreement agreement1 = mapper.map(agreementPostDTO, Agreement.class);

        ResearchProject project = researchProjectService.findReseachProjectById(agreementPostDTO.getResearchProjectId());
        Patient patient = patientRepository.findById(agreementPostDTO.getPatientId()).orElseThrow(()-> new RuntimeException("patient not found"));

        patient.getAgreements().add(agreement1);
        project.getPatientList().add(patient);
        patientRepository.save(patient);
        researchProjectRepository.save(project);
        return agreement1.getId();
    }

    public void withdrawAgreement(AgreementGetDTO agreementGetDTO){

        Agreement agreement = findAgreementById(agreementGetDTO.getId());
        ResearchProject project = mapper.map(researchProjectService.findReseachProjectById(agreementGetDTO.getResearchProjectId()), ResearchProject.class);
        Patient patient = mapper.map(patientRepository.findById(agreementGetDTO.getPatientId()),Patient.class);

        agreement.setSignOutDate(LocalDate.now());
        agreement.setIsActive(false);
        project.getPatientList().remove(patient);

        researchProjectRepository.save(project);
        agreementRepository.save(agreement);

    }


    public Agreement findAgreementById(UUID id){
        var agreement = agreementRepository.findById(id);
        return mapper.map(agreement,Agreement.class);
    }


    public List<AgreementGetDTO> findAllAgreementsByUserId(UUID id){
        List<Agreement> agreementList = agreementRepository.findAllByPatientId(id);
        List<AgreementGetDTO> agreementGetDTOList = agreementList.stream()
                .map(agreement -> mapper.map(agreement, AgreementGetDTO.class))
                .collect(Collectors.toList());
        return agreementGetDTOList;
    }




}
