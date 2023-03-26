package com.example.medicalcenter.service.patient;


import com.example.medicalcenter.exceptions.InvalidDataException;
import com.example.medicalcenter.model.UserData;
import com.example.medicalcenter.repository.PatientRepository;
import com.example.medicalcenter.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientValidator {

    private final PatientRepository patientRepository;

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private static final String PESEL_PATTERN = "^(\\\\d{2})(\\\\d{2})(\\\\d{2})(\\\\d{2})(\\\\d{3})([0-9]|[1-9][0-9])(\\\\d)$\n";
    private static final String PHONE_PATTERN = "^\\\\d{9}$\n";
    private final UserDataRepository userDataRepository;

    public boolean validateEmail(String email){
        if(userDataRepository.existsByEmail(email)){
            throw new InvalidDataException("Given email is taken");
        }
        return email.matches(EMAIL_PATTERN);
    }

    public void validatePatientData(UserData userData){

        if(!userData.getPesel().matches(PESEL_PATTERN))
            throw new InvalidDataException("Given pesel is invalid");
        if(userData.getPassword().length() < 3)
            throw new InvalidDataException("Given password is too short");
        if(!userData.getPhoneNumber().matches(PHONE_PATTERN))
            throw new InvalidDataException("Given phone number is invalid");
        validateEmail(userData.getEmail());

    }












}
