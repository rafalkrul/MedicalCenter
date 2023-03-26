package com.example.medicalcenter.repository;

import com.example.medicalcenter.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    Boolean existsByEmail (String email);

    UserData findByEmail(String email);


}
