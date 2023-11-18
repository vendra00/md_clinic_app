package com.gv.md_clinic_app.service.patient;

import com.gv.md_clinic_app.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminPatientService {
    Page<Patient> findAllPatients(Pageable pageable);
    Patient updatePatientByAdmin(Long id, Patient patient);
    void deletePatient(Long id);
}
