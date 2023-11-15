package com.gv.md_clinic_app.controller;

import com.gv.md_clinic_app.model.Patient;
import com.gv.md_clinic_app.service.patient.AdminPatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminPatientService patientService;

    @Autowired
    public AdminController(AdminPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<Page<Patient>> getAllPatients(@PageableDefault() Pageable pageable) {
        log.info("Get all patients");
        return ResponseEntity.ok(patientService.findAllPatients(pageable));
    }
}
