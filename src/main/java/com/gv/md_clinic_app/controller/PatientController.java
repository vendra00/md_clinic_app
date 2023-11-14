package com.gv.md_clinic_app.controller;

import com.gv.md_clinic_app.model.Patient;
import com.gv.md_clinic_app.service.patient.AdminPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final AdminPatientService patientService;

    public PatientController(AdminPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(patientService.findAllPatients(pageable));
    }
}
