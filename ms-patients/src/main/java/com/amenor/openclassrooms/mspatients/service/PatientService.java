package com.amenor.openclassrooms.mspatients.service;

import com.amenor.openclassrooms.mspatients.model.Patient;
import com.amenor.openclassrooms.mspatients.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(UUID id) {
        return patientRepository.findById(id);
    }

    public Patient addPatient(Patient patient) {
        patient.setId(UUID.randomUUID());
        return patientRepository.save(patient);
    }

    public Boolean updatePatient(Patient patient, UUID id) {
        Optional<Patient> oldPatient = patientRepository.findById(id);

        if (oldPatient.isPresent()) {
            Patient newDataPatient = oldPatient.get();
            newDataPatient.setFirstName(patient.getFirstName());
            newDataPatient.setLastName(patient.getLastName());
            newDataPatient.setGender(patient.getGender());
            newDataPatient.setBirthDate(patient.getBirthDate());
            newDataPatient.setAddress(patient.getAddress());
            newDataPatient.setPhone(patient.getPhone());
            patientRepository.save(newDataPatient);
            return true;
        }
        return false;
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}
