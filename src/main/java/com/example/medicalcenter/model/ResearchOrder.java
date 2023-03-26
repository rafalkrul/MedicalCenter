package com.example.medicalcenter.model;

//import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ResearchOrder")
public class ResearchOrder {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Patient patient;

    //Nazwa badania
    @Column(nullable = false)
    private String researchName;

    //data Zlecenia
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate orderedOn;

    //terminWykonaniaBadania
    @Column(nullable = false)
    private LocalDate dateOfExamination;

    @Column(nullable = false)
    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResult> testResultList;

}
