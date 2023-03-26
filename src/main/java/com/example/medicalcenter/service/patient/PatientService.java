package com.example.medicalcenter.service.patient;


import com.example.medicalcenter.DTO.patient.PatientDataDTO;
import com.example.medicalcenter.DTO.patient.PatientGetDTO;
import com.example.medicalcenter.model.Patient;
import com.example.medicalcenter.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;

    private final PatientValidator patientValidator;
    private final ModelMapper mapper;


    public UUID createPatient (PatientDataDTO patientDataDTO){

        Patient patient = mapper.map(patientDataDTO,Patient.class);

//        patientValidator.validatePatientData(patientDataDTO.getUserData());



        patient.getUserData().setPassword(new BCryptPasswordEncoder().encode(patientDataDTO.getUserData().getPassword()));

        patient.setUserData(patientDataDTO.getUserData());

        patientRepository.save(patient);

        return patient.getId();
    }

    public void deletePatientData(UUID id){patientRepository.deleteById(id);}


    public void editPatientData (PatientGetDTO patientGetDTO){

        Patient patient = findPatientById(patientGetDTO.getId());

        patientValidator.validatePatientData(patientGetDTO.getUserData());

        if(!patientGetDTO.getUserData().getEmail().isEmpty()){
            patient.getUserData().setEmail(patientGetDTO.getUserData().getEmail());
        }
        if(!patientGetDTO.getUserData().getAdress().isEmpty()){
            patient.getUserData().setAdress(patientGetDTO.getUserData().getAdress());
        }

        if(!patientGetDTO.getUserData().getPhoneNumber().isEmpty()){
            patient.getUserData().setPhoneNumber(patientGetDTO.getUserData().getPhoneNumber());
        }

        if(!patientGetDTO.getUserData().getPassword().isEmpty()){
            patient.getUserData().setPassword(
                    new BCryptPasswordEncoder().encode(patientGetDTO.getUserData().getPassword())
            );
        }

        if(!patientGetDTO.getUserData().getFirstName().isEmpty()){
            patient.getUserData().setFirstName(patientGetDTO.getUserData().getFirstName());
        }

        if(!patientGetDTO.getUserData().getLastName().isEmpty()){
            patient.getUserData().setLastName(patientGetDTO.getUserData().getLastName());
        }

        patientRepository.save(patient);

    }

    public Patient findPatientById(UUID id){
        var patient = patientRepository.findById(id);
        return mapper.map(patient,Patient.class);
    }

    public PatientDataDTO getPatientById(UUID id){
        return mapper.map(patientRepository.findById(id), PatientDataDTO.class);
    }







}
