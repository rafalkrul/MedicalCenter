package com.example.medicalcenter.model;

//import jakarta.persistence.*;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Agreement")
public class Agreement {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID patientId;
    @Column(nullable = false)
    private UUID researchProjectId;

    // Data wyrażenia zgody
    @Column(nullable = false)
    private LocalDate signInDate;

    // Data wycofania zgody
    @Column
    private LocalDate signOutDate;

    @Column(nullable = false)
    private Boolean isActive = true;

}
