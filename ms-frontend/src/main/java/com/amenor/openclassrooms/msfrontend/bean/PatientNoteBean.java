package com.amenor.openclassrooms.msfrontend.bean;

import java.util.UUID;

public class PatientNoteBean {
    public UUID patientNoteId;
    public UUID patientId;
    public String patientName;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientNote() {
        return patientNote;
    }

    public void setPatientNote(String patientNote) {
        this.patientNote = patientNote;
    }
}
