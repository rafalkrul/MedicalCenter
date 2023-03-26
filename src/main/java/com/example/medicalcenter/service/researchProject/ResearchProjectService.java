package com.example.medicalcenter.service.researchProject;


import com.example.medicalcenter.DTO.researchProject.ResearchProjectGetDTO;
import com.example.medicalcenter.DTO.researchProject.ResearchProjectPostDTO;
import com.example.medicalcenter.model.Agreement;
import com.example.medicalcenter.model.ResearchProject;
import com.example.medicalcenter.repository.AgreementRepository;
import com.example.medicalcenter.repository.ResearchProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResearchProjectService {



    private final ResearchProjectRepository researchProjectRepository;
    private final AgreementRepository agreementRepository;
    private final ModelMapper mapper;


    public UUID createResearchProject (ResearchProjectPostDTO projectPostDTO){

        var project = mapper.map(projectPostDTO, ResearchProject.class);
        researchProjectRepository.save(project);

        return project.getId();
    }


    public void editResearchProject(ResearchProjectGetDTO projectGetDTO){
        ResearchProject project = findReseachProjectById(projectGetDTO.getId());

        if(!projectGetDTO.getTitle().isEmpty()){
            project.setTitle(projectGetDTO.getTitle());
        }
        if(!projectGetDTO.getDescription().isEmpty()){
            project.setDescription(projectGetDTO.getDescription());
        }
        if(projectGetDTO.getEndDate() != null){
            project.setEndDate(projectGetDTO.getEndDate());
        }

        researchProjectRepository.save(project);
    }



    public ResearchProject findReseachProjectById(UUID id){
        var project = researchProjectRepository.findById(id);
        return mapper.map(project,ResearchProject.class);
    }


    public ResearchProjectGetDTO getReseachProjectById(UUID id){
        var project = researchProjectRepository.findById(id);
        return mapper.map(project,ResearchProjectGetDTO.class);
    }

    public void closeResearchProject(UUID id){
        var project = findReseachProjectById(id);
        project.setEndDate(LocalDate.now());
        List<Agreement> agreementList = agreementRepository.findAllByResearchProjectId(id)
                .stream()
                .map(agreement -> {

                    agreement.setIsActive(false);


                    return agreement;
                })
                .collect(Collectors.toList());
        researchProjectRepository.save(project);
    }

    public void deleteResearchProject(UUID id){
        researchProjectRepository.deleteById(id);
    }

}
