package com.example.medicalcenter.controller;


import com.example.medicalcenter.DTO.agreement.AgreementGetDTO;
import com.example.medicalcenter.DTO.agreement.AgreementPostDTO;
import com.example.medicalcenter.model.Agreement;
import com.example.medicalcenter.service.agreement.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AgreementController {


    private final AgreementService agreementService;

    @PostMapping("/agreement")
    public ResponseEntity<UUID> addAgreement(@RequestBody AgreementPostDTO agreementPostDTO){
        var agreement = agreementService.createAgreement(agreementPostDTO);
        return new ResponseEntity<>(agreement, HttpStatus.CREATED);
    }

    @PostMapping("/withdrawAgreement")
    public ResponseEntity<Void> withdrawAgreement(@RequestBody AgreementGetDTO agreementGetDTO){
        agreementService.withdrawAgreement(agreementGetDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/agreement/{id}")
    public ResponseEntity<Agreement> findAgreementById(@PathVariable UUID id){
        var agreement = agreementService.findAgreementById(id);
        return new ResponseEntity<>(agreement,HttpStatus.OK);
    }

    @GetMapping("/patientAgreements/{id}")
    public List<AgreementGetDTO> findAllByUserId(@PathVariable UUID id){
        var agreementList = agreementService.findAllAgreementsByUserId(id);
        return agreementList;
    }

}
