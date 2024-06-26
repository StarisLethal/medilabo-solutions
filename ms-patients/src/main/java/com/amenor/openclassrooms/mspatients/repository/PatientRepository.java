package com.amenor.openclassrooms.mspatients.repository;

import com.amenor.openclassrooms.mspatients.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends MongoRepository<Patient, UUID> {
}
