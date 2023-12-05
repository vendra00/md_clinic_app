package com.gv.md_clinic_app.service.patient;

import com.gv.md_clinic_app.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface AdminPatientService declares methods for admin to manage patients.
 * @see Patient
 * @see Page
 * @see Pageable
 */
public interface AdminPatientService {

    /**
     * Method findAllPatients returns all patients.
     * @param pageable - pageable object
     * @return Page<Patient> - page with patients
     */
    Page<Patient> findAllPatients(Pageable pageable);

    /**
     * Method findPatientById returns patient by id.
     * @param id - patient id
     * @param patient - patient object
     * @return Patient - patient object
     */
    Patient updatePatientByAdmin(Long id, Patient patient);

    /**
     * Method deletePatient deletes patient by id.
     * @param id - patient id
     */
    void deletePatient(Long id);
}
