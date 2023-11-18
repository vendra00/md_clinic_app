package com.gv.md_clinic_app.service.patient;

import com.gv.md_clinic_app.model.patient.Patient;

import java.util.List;
import java.util.Optional;

public interface DoctorPatientService {
    Patient registerPatient(Patient patient);
    Patient updatePatientByDoctor(Long id, Patient patient);
    List<Patient> findPatientsByDoctor(Long doctorId);
    Optional<Patient> findPatientById(Long id);
}
