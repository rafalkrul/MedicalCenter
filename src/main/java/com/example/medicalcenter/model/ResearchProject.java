package com.example.medicalcenter.model;

//import jakarta.persistence.*;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ResearchProject")
public class ResearchProject {


    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    // Data rozpoczęcia testu
    @Column(nullable = false)
    private LocalDate startDate;

    // Data zakończenia testu
    @Column
    private LocalDate endDate;

    @OneToMany
    private List<Patient> patientList;


}
