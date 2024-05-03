package com.amenor.openclassrooms.mspatientnote.service;

import com.amenor.openclassrooms.mspatientnote.model.PatientNote;
import com.amenor.openclassrooms.mspatientnote.repository.PatientNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientNoteService {

    private PatientNoteRepository patientNoteRepository;

    public PatientNoteService(PatientNoteRepository patientNoteRepository) {
        this.patientNoteRepository = patientNoteRepository;
    }

    public List<PatientNote> getAllPatientNotes() {
        return patientNoteRepository.findAll();
    }

    //TODO un get de toute les note lié a l'id d'un patient dans le repo
    public List<PatientNote> getPatientNotebyPatientId(UUID patientId) {
        return patientNoteRepository.findByPatientId(patientId);
    }

    public Optional<PatientNote> getPatientNoteByID(UUID PatientNoteId) {
        return patientNoteRepository.findById(PatientNoteId);
    }

    public PatientNote createPatientNote(PatientNote patientNote, UUID patientId, String lastName) {
        patientNote.setPatientNoteId(UUID.randomUUID());
        patientNote.setPatientId(patientId);
        patientNote.setPatientName(lastName);
        return patientNoteRepository.save(patientNote);
    }
}
