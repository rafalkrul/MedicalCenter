package com.example.medicalcenter.service.researchOrder;


import com.example.medicalcenter.DTO.reaserchOrder.ResearchOrderGetDTO;
import com.example.medicalcenter.DTO.reaserchOrder.ResearchOrderPostDTO;
import com.example.medicalcenter.constants.Status;
import com.example.medicalcenter.model.Agreement;
import com.example.medicalcenter.model.ResearchOrder;
import com.example.medicalcenter.model.TestResult;
import com.example.medicalcenter.repository.AgreementRepository;
import com.example.medicalcenter.repository.PatientRepository;
import com.example.medicalcenter.repository.ResearchOrderRepository;
import com.example.medicalcenter.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResearchOrderService {


    private final ResearchOrderRepository researchOrderRepository;

    private final AgreementRepository agreementRepository;

    private final ModelMapper mapper;
    private final PatientRepository patientRepository;
    private final TestResultRepository testResultRepository;


    public UUID createResearchOrder(ResearchOrderPostDTO researchOrderPostDTO){
        var researchOrder = mapper.map(researchOrderPostDTO, ResearchOrder.class);

        Agreement agreement = agreementRepository.findAgreementByPatientIdAndAndResearchProjectId(researchOrderPostDTO.getPatientId(),researchOrderPostDTO.getResearchProjectId());

        if(agreement.getIsActive()){
            researchOrder.setTestResultList(new ArrayList<>());
           researchOrderRepository.save(researchOrder);
            return researchOrder.getId();
        }else{
            throw new RuntimeException("nie ma agreementu");
        }


    }

    public void addTestResultsToResearchOrder(ResearchOrderGetDTO researchOrderGetDTO){

        var researchOrder = researchOrderMapper(researchOrderGetDTO);

        var testResults = researchOrderGetDTO.getTestResultListDTO()
                .stream()
                .map(testResult -> mapper.map(testResult, TestResult.class))
                .collect(Collectors.toList());

        researchOrder.getTestResultList().addAll(testResults);

        researchOrder.setStatus(String.valueOf(Status.COMPLETED));

        researchOrderRepository.save(researchOrder);

    }

    public ResearchOrder researchOrderMapper(ResearchOrderGetDTO researchOrderGetDTO){
        var order = researchOrderRepository.findById(researchOrderGetDTO.getId());
        return mapper.map(order, ResearchOrder.class);
    }


}
