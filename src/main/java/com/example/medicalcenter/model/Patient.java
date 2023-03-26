package com.example.medicalcenter.model;

//import jakarta.persistence.*;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UserData userData;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agreement> agreements;

    @OneToMany(mappedBy = "patient")
    private List<ResearchOrder> researchOrderList;


}
