package com.example.medicalcenter.controller;


import com.example.medicalcenter.DTO.reaserchOrder.ResearchOrderGetDTO;
import com.example.medicalcenter.DTO.reaserchOrder.ResearchOrderPostDTO;
import com.example.medicalcenter.service.researchOrder.ResearchOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResearchOrderController {


    private final ResearchOrderService researchOrderService;


    @PostMapping("/researchOrder")
    public ResponseEntity<UUID> createResearchOrder(@RequestBody ResearchOrderPostDTO researchOrderPostDTO){
        var researchOrder = researchOrderService.createResearchOrder(researchOrderPostDTO);
        return new ResponseEntity<>(researchOrder, HttpStatus.CREATED);
    }


    @PostMapping("/addTestResults")
    public ResponseEntity<Void> addTestResultsToOrder(@RequestBody ResearchOrderGetDTO researchOrderGetDTO){
        researchOrderService.addTestResultsToResearchOrder(researchOrderGetDTO);
        return ResponseEntity.ok().build();
    }


}
