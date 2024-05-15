package com.amenor.openclassrooms.mspatientnote.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "patientNotes")
public class PatientNote {

    @Id
    public UUID patientNoteId;
    @NotNull
    public UUID patientId;
    @NotEmpty
    public String patientNote;

    public UUID getPatientNoteId() {
        return patientNoteId;
    }

    public void setPatientNoteId(UUID patientNoteId) {
        this.patientNoteId = patientNoteId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(String patientNote) {
        this.patientNote = patientNote;
    }
}
