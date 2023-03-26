package com.example.medicalcenter.controller;




import com.example.medicalcenter.DTO.userData.AuthenticationRequest;

import com.example.medicalcenter.config.security.MyUserDetails;
import com.example.medicalcenter.config.security.MyUserDetailsService;
import com.example.medicalcenter.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("bad credentials");
        }
        MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        var jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

}
