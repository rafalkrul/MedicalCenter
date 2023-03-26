package com.example.medicalcenter.DTO.reaserchOrder;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ResearchOrderPostDTO {

    private UUID patientId;
    private UUID researchProjectId;
    private String researchName;
    private LocalDate dateOfExamination;
    private String status;
}
