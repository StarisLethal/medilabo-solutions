package com.amenor.openclassrooms.mspatientnote.repository;

import com.amenor.openclassrooms.mspatientnote.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientNoteRepository extends MongoRepository<PatientNote, UUID> {
    List<PatientNote> findByPatientId(UUID patientId);

    @Query (value = "{ 'patientId' : ?0 }", fields = "{ 'patientNote' : 1}")
    List<String> findPatientNoteByPatientId(UUID patientId);
}
