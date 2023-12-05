package com.gv.md_clinic_app.repository;

import com.gv.md_clinic_app.model.patient.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Condition repository interface
 */
@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
    List<Condition> findByName(String name);

    // Method to count patients with a specific condition
    @Query("SELECT COUNT(p) FROM Patient p JOIN p.conditions c WHERE c.name = :conditionName")
    long countPatientsWithCondition(@Param("conditionName") String conditionName);
}

