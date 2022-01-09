package com.mediscreen.patient.controllers;

import java.util.List;

import com.mediscreen.patient.models.Patient;
import com.mediscreen.patient.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> create(@RequestBody Patient patient) throws Exception {
        return new ResponseEntity<>(patientService.create(patient), HttpStatus.OK);
    }

    @GetMapping(value = "/patient/all")
    public ResponseEntity<List<Patient>> getAll() {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/patient/update")
    public ResponseEntity<Patient> update(@RequestBody Patient patient) throws Exception {
        return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/patient/delete/{id}")
    public ResponseEntity<Patient> delete(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(patientService.delete(id), HttpStatus.OK);
    }
}
