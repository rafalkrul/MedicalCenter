package com.example.medicalcenter.controller;


import com.example.medicalcenter.DTO.patient.PatientDataDTO;
import com.example.medicalcenter.DTO.patient.PatientGetDTO;
import com.example.medicalcenter.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;



    @PostMapping("/patient")
    public ResponseEntity<UUID> addPatient(@RequestBody PatientDataDTO patientDataDTO) {
        var patient = patientService.createPatient(patientDataDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @DeleteMapping("/patient/{id}/delete")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatientData(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/editPatient")
    public ResponseEntity<Void> editPatient(@RequestBody PatientGetDTO patientGetDTO){
        patientService.editPatientData(patientGetDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/patient/{id}")
    public PatientDataDTO getPatientById(@PathVariable UUID id){
        return patientService.getPatientById(id);
    }

}
