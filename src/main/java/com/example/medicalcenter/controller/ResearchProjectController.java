package com.example.medicalcenter.controller;


import com.example.medicalcenter.DTO.researchProject.ResearchProjectGetDTO;
import com.example.medicalcenter.DTO.researchProject.ResearchProjectPostDTO;
import com.example.medicalcenter.service.researchProject.ResearchProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResearchProjectController {

    private final ResearchProjectService researchProjectService;


    @PostMapping("/researchProject")
    public ResponseEntity<UUID> addResearchProject(@RequestBody ResearchProjectPostDTO researchProjectPostDTO) {
        var project = researchProjectService.createResearchProject(researchProjectPostDTO);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/researchProject/{id}")
    public ResponseEntity<ResearchProjectGetDTO> getResearchProjectById(@PathVariable UUID id){
        var project = researchProjectService.getResearchProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/closeProject/{id}")
    public void closeResearchProject(@PathVariable UUID id){
        researchProjectService.closeResearchProject(id);
    }

    @DeleteMapping("/deleteResearchProject/{id}")
    public void deleteResearchProject(@PathVariable UUID id){
        researchProjectService.deleteResearchProject(id);
    }


    @PostMapping("/editResearchProject")
    public void editResearchProject(@RequestBody ResearchProjectGetDTO researchProjectGetDTO) {
        researchProjectService.editResearchProject(researchProjectGetDTO);
    }

}
