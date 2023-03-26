package com.example.medicalcenter.DTO.agreement;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AgreementPostDTO {

    private UUID patientId;
    private UUID researchProjectId;
    private LocalDate signInDate;
    private Boolean isActive;


}
