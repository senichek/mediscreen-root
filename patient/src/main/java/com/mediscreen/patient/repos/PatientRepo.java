package com.mediscreen.patient.repos;

import com.mediscreen.patient.models.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
}
