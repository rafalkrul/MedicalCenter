package com.example.medicalcenter.DTO.researchProject;


import com.example.medicalcenter.model.Patient;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ResearchProjectGetDTO {

    private UUID id;
    private String title;
    private String description;
    // Data rozpoczęcia testu
    private LocalDate startDate;
    // Data zakończenia testu
    private LocalDate endDate;
    private List<Patient> patientList;
}
