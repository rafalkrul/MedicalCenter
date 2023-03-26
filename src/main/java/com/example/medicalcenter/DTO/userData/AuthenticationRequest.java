package com.example.medicalcenter.DTO.userData;

import lombok.Data;


@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
