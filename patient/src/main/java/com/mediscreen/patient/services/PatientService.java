package com.mediscreen.patient.services;

import java.util.List;
import java.util.Map;

import com.mediscreen.patient.models.Patient;

public interface PatientService {

    public Patient create(Patient p) throws Exception;
    public List<Patient> getAll();
    public Patient getPatientById(Integer id) throws Exception;
    public Patient update(Patient p) throws Exception;
    public Map<String, String> validate(Patient user);
    public Patient delete(Integer id) throws Exception;
}
