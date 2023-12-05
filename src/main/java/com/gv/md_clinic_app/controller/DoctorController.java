package com.gv.md_clinic_app.controller;

import com.gv.md_clinic_app.model.patient.Patient;
import com.gv.md_clinic_app.model.dto.patient.PatientDto;
import com.gv.md_clinic_app.model.enums.UserRole;
import com.gv.md_clinic_app.service.patient.DoctorPatientService;
import com.gv.md_clinic_app.utils.converter.PatientConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Doctor controller.
 */
@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    /**
     * Doctor patient service.
     */
    private final DoctorPatientService doctorPatientService;

    /**
     * Constructor.
     *
     * @param doctorPatientService doctor patient service.
     */
    @Autowired
    public DoctorController(DoctorPatientService doctorPatientService) {
        this.doctorPatientService = doctorPatientService;
    }

    /**
     * Register patient.
     *
     * @param patientDto patient dto.
     * @return registered patient.
     */
    @PostMapping("/register-patient")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<PatientDto> registerPatient(@RequestBody PatientDto patientDto) {
        log.info("Registering patient: {}", patientDto);
        Patient patient = PatientConverter.convertToEntity(patientDto);
        Patient registeredPatient = doctorPatientService.registerPatient(patient);
        PatientDto registeredPatientDto = PatientConverter.convertToDto(registeredPatient, UserRole.DOCTOR);

        return ResponseEntity.ok(registeredPatientDto);
    }
}
