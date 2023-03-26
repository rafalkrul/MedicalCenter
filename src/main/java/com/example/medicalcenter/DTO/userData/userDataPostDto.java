package com.example.medicalcenter.DTO.userData;


import com.example.medicalcenter.constants.Role;
import lombok.Data;

@Data
public class userDataPostDto {

    private String name;
    private String surname;
    private String pesel;
    private String phoneNumber;
    private String email;
    private String adress;
    private Role role;

}
