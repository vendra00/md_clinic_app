package com.gv.md_clinic_app.repository;

import com.gv.md_clinic_app.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByLastName(String lastName);

    //List<Patient> findPatientsByDoctorId(Long );
}

