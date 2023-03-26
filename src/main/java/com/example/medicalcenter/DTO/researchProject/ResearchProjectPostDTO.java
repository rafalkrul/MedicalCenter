package com.example.medicalcenter.DTO.researchProject;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResearchProjectPostDTO {

    private String title;
    private String description;

    // Data rozpoczęcia testu
    private LocalDate startDate;

    // Data zakończenia testu
    private LocalDate endDate;



}
