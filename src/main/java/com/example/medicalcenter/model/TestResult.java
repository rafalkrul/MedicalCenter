package com.example.medicalcenter.model;

//import jakarta.persistence.*;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TestResults")
public class TestResult {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String testName;

    @Column(nullable = false)
    private String result;

}
