package com.amenor.openclassrooms.mspatientnote.service;

import com.amenor.openclassrooms.mspatientnote.model.PatientNote;
import com.amenor.openclassrooms.mspatientnote.repository.PatientNoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class PatientNoteService {

    final private PatientNoteRepository patientNoteRepository;

    public PatientNoteService(PatientNoteRepository patientNoteRepository) {
        this.patientNoteRepository = patientNoteRepository;
    }

    public List<PatientNote> getAllPatientNotes() {
        return patientNoteRepository.findAll();
    }

    public List<PatientNote> getPatientNotebyPatientId(UUID patientId) {
        return patientNoteRepository.findByPatientId(patientId);
    }

    public Optional<PatientNote> getPatientNoteByID(UUID PatientNoteId) {
        return patientNoteRepository.findById(PatientNoteId);
    }

    public PatientNote createPatientNote(PatientNote patientNote) {
        patientNote.setPatientNoteId(UUID.randomUUID());
        return patientNoteRepository.save(patientNote);
    }

    //TODO 2 method one with mongodb word count one in java

    public Integer symptomCount(UUID id) {
        List<String> list = patientNoteRepository.findPatientNoteByPatientId(id);

        List<String> noteToCount = list.stream()
                .map(note -> note.replaceAll("\\p{Punct}", "").toLowerCase())
                .toList();

        Map<String, String> symptomList = new HashMap<>();
        symptomList.put("fume", "fume(ur|use|r)?");
        symptomList.put("hémoglobine", "hémoglobine a1c");
        symptomList.put("microalbumine", "microalbumine");
        symptomList.put("taille", "taille");
        symptomList.put("poids", "poids(?!\\s+(égal ou inférieur|recommandé))");
        symptomList.put("anormal", "anormal");
        symptomList.put("cholestérol", "cholestérol");
        symptomList.put("vertige", "vertige(s)?");
        symptomList.put("rechute", "rechute");
        symptomList.put("réaction", "réaction");
        symptomList.put("anticorps", "anticorps");

        Integer symptomCount = 0;
        for (Map.Entry<String, String> entry : symptomList.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getValue());
            for (String note : noteToCount) {
/*                System.out.println("Checking note: [" + note + "] for symptom regex: [" + entry.getValue() + "]");*/
                if (pattern.matcher(note).find()) {
                    symptomCount++;

/*                    System.out.println("Match found in note: [" + note + "] for symptom: " + symptom);*/
                    break;
                }
            }
        }
        System.out.println(symptomCount);
        return symptomCount;
    }


    public Boolean diabeteEarlyOnSet(String birthDate, String gender, UUID id) {
        Integer symptom = symptomCount(id);
        Integer age = getAge(birthDate);
        if ((gender.equals("M") && age < 30 && symptom > 5) || (gender.equals("F") && age < 30 && symptom > 7) || (age >= 30 && symptom > 8)) {
            System.out.println(symptom);
            return true;
        }
        return false;
    }

    public Boolean diabeteDanger(String birthDate, String gender, UUID id) {
        Integer symptom = symptomCount(id);
        Integer age = getAge(birthDate);
        if ((gender.equals("M") && age < 30 && symptom == 3) || (gender.equals("F") && age < 30 && symptom == 4) || (age >= 30 && symptom == 6 || symptom == 7)) {
            System.out.println(symptom);
            return true;
        }
        return false;
    }

    public Boolean diabeteBorderLine(String birthDate, UUID id) {
        Integer symptom = symptomCount(id);
        Integer age = getAge(birthDate);
        if ((age >= 30) && (symptom >= 2 && symptom <= 5)) {
            System.out.println(symptom);
            return true;
        }
        return false;
    }

    public Boolean diabeteNone(UUID id) {
        if (symptomCount(id) == 0) {
            System.out.println(symptomCount(id));
            return true;
        }
        return false;
    }

    public String diabeteDiagnose(String birthDate, String gender, UUID id) {
        if (diabeteEarlyOnSet(birthDate, gender, id)) {
            return "Early onset";
        }
        if (diabeteDanger(birthDate, gender, id)) {
            return "In Danger";
        }
        if (diabeteBorderLine(birthDate, id)) {
            return "Borderline";
        }
        if (diabeteNone(id)) {
            return "None";
        } else {
            return "Not enough data";
        }
    }

    public Integer getAge(String birthDate) {
        LocalDate birthday = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();
    }
}
