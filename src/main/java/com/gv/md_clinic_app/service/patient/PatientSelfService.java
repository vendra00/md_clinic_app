package com.gv.md_clinic_app.service.patient;

import com.gv.md_clinic_app.model.Patient;

import java.util.Optional;

public interface PatientSelfService {
    Optional<Patient> getPatientDetails(Long patientId);
    Patient updatePatientInfo(Long patientId, Patient patient);
}
