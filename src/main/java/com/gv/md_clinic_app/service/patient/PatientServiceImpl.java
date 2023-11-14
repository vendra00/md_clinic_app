package com.gv.md_clinic_app.service.patient;

import com.gv.md_clinic_app.model.Patient;
import com.gv.md_clinic_app.repository.PatientRepository;
import com.gv.md_clinic_app.utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientServiceImpl implements PatientSelfService, DoctorPatientService, AdminPatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('DOCTOR')")
    public Patient registerPatient(Patient patient) {
        log.info("IN PatientServiceImpl registerPatient {}", patient);
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('PATIENT')")
    public Patient updatePatientInfo(Long id, Patient updatedPatient) {
        log.info("IN PatientServiceImpl updatePatient {}", updatedPatient);
        Long currentPatientId = SecurityUtils.getCurrentAuthenticatedPersonId();
        if (!currentPatientId.equals(id)) {
            log.error("IN PatientServiceImpl updatePatient: patient with id {} attempted to update patient with id {}", currentPatientId, id);
            throw new AccessDeniedException("You do not have permission to access these details.");
        } else {
            return patientRepository.findById(id)
                    .map(patient -> {
                        patient.setFirstName(updatedPatient.getFirstName());
                        patient.setLastName(updatedPatient.getLastName());
                        patient.setGender(updatedPatient.getGender());
                        patient.setDob(updatedPatient.getDob());
                        patient.setAddress(updatedPatient.getAddress());
                        patient.setPhone(updatedPatient.getPhone());
                        patient.setEmail(updatedPatient.getEmail());
                        patient.setEmergencyContact(updatedPatient.getEmergencyContact());

                        return patientRepository.save(patient);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('DOCTOR')")
    public Patient updatePatientByDoctor(Long id, Patient updatedPatient) {
        log.info("IN PatientServiceImpl updatePatient {}", updatedPatient);
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setBloodType(updatedPatient.getBloodType());
                    patient.setGender(updatedPatient.getGender());
                    patient.setMedicalHistory(updatedPatient.getMedicalHistory());
                    patient.setConditions(updatedPatient.getConditions());
                    patient.setIsOrganDonor(updatedPatient.getIsOrganDonor());

                    return patientRepository.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Patient updatePatientByAdmin(Long id, Patient updatedPatient) {
        log.info("IN PatientServiceImpl updatePatient {}", updatedPatient);
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setFirstName(updatedPatient.getFirstName());
                    patient.setLastName(updatedPatient.getLastName());
                    patient.setBloodType(updatedPatient.getBloodType());
                    patient.setGender(updatedPatient.getGender());
                    patient.setDob(updatedPatient.getDob());
                    patient.setAddress(updatedPatient.getAddress());
                    patient.setPhone(updatedPatient.getPhone());
                    patient.setEmail(updatedPatient.getEmail());
                    patient.setMedicalHistory(updatedPatient.getMedicalHistory());
                    patient.setConditions(updatedPatient.getConditions());
                    patient.setEmergencyContact(updatedPatient.getEmergencyContact());
                    patient.setIsOrganDonor(updatedPatient.getIsOrganDonor());

                    return patientRepository.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Patient> findPatientsByDoctor(Long doctorId) {
        log.info("IN PatientServiceImpl findPatientsByDoctor {}", doctorId);
        //return patientRepository.findPatientsByDoctorId(doctorId);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('PATIENT')")
    public Optional<Patient> findPatientById(Long id) {
        log.info("IN PatientServiceImpl findPatientById {}", id);
        return patientRepository.findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Patient> findAllPatients(Pageable pageable) {
        log.info("IN PatientServiceImpl findAllPatients");
        return patientRepository.findAll(pageable);
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePatient(Long id) {
        log.info("IN PatientServiceImpl deletePatient {}", id);
        patientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('PATIENT')")
    public Optional<Patient> getPatientDetails(Long patientId) {
        log.info("IN PatientServiceImpl getPatientDetails {}", patientId);
        // Assuming you have a way to get the currently authenticated patient's ID
        Long currentPatientId = SecurityUtils.getCurrentAuthenticatedPersonId();
        if (!currentPatientId.equals(patientId)) {
            log.error("IN PatientServiceImpl getPatientDetails - You do not have permission to access these details.");
            throw new AccessDeniedException("You do not have permission to access these details.");
        }
        log.info("IN PatientServiceImpl getPatientDetails - Patient details accessed successfully.");
        return patientRepository.findById(patientId);
    }
}
