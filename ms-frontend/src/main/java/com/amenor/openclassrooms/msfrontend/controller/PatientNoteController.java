package com.amenor.openclassrooms.msfrontend.controller;

import com.amenor.openclassrooms.msfrontend.bean.PatientNoteBean;
import com.amenor.openclassrooms.msfrontend.proxies.PatientNoteProxy;
import com.amenor.openclassrooms.msfrontend.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class PatientNoteController {

    private final PatientNoteProxy patientNoteProxy;


    public PatientNoteController(PatientNoteProxy patientNoteProxy) {
        this.patientNoteProxy = patientNoteProxy;
    }

    @GetMapping("/patientNotes")
    public String patientNotes(Model model, @RequestParam UUID id) {
        model.addAttribute("patientNotes", patientNoteProxy.getPatientNoteByPatientId(id));
        return "patientNotes";
    }

    @GetMapping("/newNote")
    public String newNote(Model model, PatientNoteBean patientNoteBean) {
        PatientNoteBean newPatientNoteBean = new PatientNoteBean();
        newPatientNoteBean.setPatientId(patientNoteBean.getPatientId());
        model.addAttribute("patientNote", patientNoteBean);
        return "newNote";
    }

    @PostMapping("/createNote")
    public String createNote(Model model, @ModelAttribute PatientNoteBean patientNoteBean) {
        patientNoteProxy.createPatientNote(patientNoteBean);
        return "redirect:/patientNotes?id=" + patientNoteBean.getPatientId();
    }
}
