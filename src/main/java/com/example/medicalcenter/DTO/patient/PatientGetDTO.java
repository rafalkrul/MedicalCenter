package com.example.medicalcenter.DTO.patient;


import com.example.medicalcenter.DTO.reaserchOrder.ResearchOrderGetDTO;
import com.example.medicalcenter.model.Agreement;
import com.example.medicalcenter.model.UserData;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PatientGetDTO {
    private UUID id;
    private UserData userData;
    private List<Agreement> agreements;
    private List<ResearchOrderGetDTO> researchOrderListGetDTO;
}
