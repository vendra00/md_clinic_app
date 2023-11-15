package com.gv.md_clinic_app.controller;

import com.gv.md_clinic_app.model.dto.PatientDto;
import com.gv.md_clinic_app.model.enums.UserRole;
import com.gv.md_clinic_app.service.patient.PatientSelfService;
import com.gv.md_clinic_app.utils.SecurityUtils;
import com.gv.md_clinic_app.utils.converter.PatientConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientSelfService patientService;

    @Autowired
    public PatientController(PatientSelfService patientService) {this.patientService = patientService;}


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientDto> getPatientDetails(@PathVariable Long id) {
        UserRole currentUserRole = SecurityUtils.getCurrentUserRole();
        return patientService.getPatientDetails(id)
                .map(patient -> PatientConverter.convertToDto(patient, currentUserRole))
                .map(ResponseEntity::ok) // Wrap the PatientDto in a ResponseEntity
                .orElse(ResponseEntity.notFound().build());
    }
}
