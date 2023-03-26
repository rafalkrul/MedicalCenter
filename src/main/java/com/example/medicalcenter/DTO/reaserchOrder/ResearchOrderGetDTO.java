package com.example.medicalcenter.DTO.reaserchOrder;


import com.example.medicalcenter.DTO.testResult.TestResultDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ResearchOrderGetDTO {

    private UUID id;
    private String researchName;
    private LocalDate dateOfExamination;
    private String status;
    private List<TestResultDTO> testResultListDTO;
}
