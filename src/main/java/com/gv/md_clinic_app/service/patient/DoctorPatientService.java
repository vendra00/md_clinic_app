package com.gv.md_clinic_app.service.patient;

import com.gv.md_clinic_app.model.patient.Patient;

import java.util.List;
import java.util.Optional;

/**
 * Interface DoctorPatientService declares methods for doctor to manage patients.
 */
public interface DoctorPatientService {
    /**
     * Method registerPatient registers patient.
     * @param patient - patient object
     * @return Patient - patient object
     */
    Patient registerPatient(Patient patient);

    /**
     * Method updatePatientByDoctor updates patient by doctor.
     * @param id - patient id
     * @param patient - patient object
     * @return Patient - patient object
     */
    Patient updatePatientByDoctor(Long id, Patient patient);

    /**
     * Method deletePatient deletes patient by id.
     * @param doctorId - doctor id
     * @return List<Patient> - list of patients
     */
    List<Patient> findPatientsByDoctor(Long doctorId);

    /**
     * Method findPatientById returns patient by id.
     * @param id - patient id
     * @return Optional<Patient> - optional patient object
     */
    Optional<Patient> findPatientById(Long id);
}
