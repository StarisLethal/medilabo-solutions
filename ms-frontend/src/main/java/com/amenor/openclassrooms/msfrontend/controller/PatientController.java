package com.amenor.openclassrooms.msfrontend.controller;

import com.amenor.openclassrooms.msfrontend.bean.PatientBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.amenor.openclassrooms.msfrontend.proxies.PatientProxy;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class PatientController {

    private final PatientProxy patientProxy;


    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<PatientBean> patients = patientProxy.getPatients();
        model.addAttribute("patients", patients);
        return "home";
    }

    @GetMapping("/newPatient")
    public String newPatient(Model model) {
        model.addAttribute("patient", new PatientBean());
        return "newPatient";
    }

    @PostMapping("/createPatient")
    public String createPatient(PatientBean patientBean) {
        patientProxy.createPatient(patientBean);
        return "redirect:";
    }

    @GetMapping("/editRecord")
    public String editRecord(Model model, String id) {
        model.addAttribute("patient", patientProxy.getPatientById(id));
        return "editRecord";
    }

    @PutMapping("/editedRecord")
    public String editRecord(@ModelAttribute PatientBean patientBean, String id, Model model) {
        model.addAttribute("patient", patientProxy.getPatientById(id));
        patientProxy.updatePatient(id, patientBean);
        return "redirect:";
    }
}
