package com.mediscreen.patient.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.mediscreen.patient.models.Patient;
import com.mediscreen.patient.repos.PatientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientServiceImpl implements PatientService {

    @Autowired
    private Validator validator;

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public Patient create(Patient p) throws Exception {
        Patient created;
        Map<String, String> validationErrors = validate(p);
        if (validationErrors.size() > 0) {
            throw new Exception(validationErrors.toString());
        } else {
            created = patientRepo.save(p);
            log.info("Created {}", created);
            return created;
        }
    }

    @Override
    public List<Patient> getAll() {
        return patientRepo.findAll();
    }

    @Override
    public Patient getPatientById(Integer id) throws Exception {
        Optional<Patient> findById = patientRepo.findById(id);
        if (findById.isPresent() == false) {
            throw new Exception((String.format("Entity with id %s does not exist.", id)));
        } else {
            return findById.get();
        }
    }

    @Override
    public Patient update(Patient p) throws Exception {
        Patient updated;
        Optional<Patient> findById = patientRepo.findById(p.getId());
        if (findById.isPresent() == false) {
            throw new Exception((String.format("Entity with id %s does not exist.", p.getId())));
        } else {
            /*
             * If the ID of the user exists in DB, DB will update the fields,
             * another user with the same ID will not be created. This is why we
             * can use <patientRepo.save> for saving and updating the Entities.
             */
            Map<String, String> validationErrors = validate(p);
            if (validationErrors.size() > 0) {
                throw new Exception(validationErrors.toString());
            } else {
                updated = patientRepo.save(p);
                log.info("Updated {}", updated);
                return updated;
            }
        }
    }

    @Override
    public Patient delete(Integer id) throws Exception {
        Optional<Patient> findById = patientRepo.findById(id);
        if (findById.isPresent() == false) {
            throw new Exception((String.format("Entity with id %s does not exist.", id)));
        } else {
            patientRepo.deleteById(id);
            log.info("Deleted {}", findById.get());
            return findById.get();
        }
    }

    @Override
    public Map<String, String> validate(Patient user) {
        Set<ConstraintViolation<Patient>> validationErrors = validator.validate(user);
        Map<String, String> errorMessages = new HashMap<>();
        /*
         * If there are validation errors we extract them and put them into the Map
         * where the Key is the name of the property and the Value is the error message.
         */
        if (validationErrors.size() > 0) {
            Iterator<ConstraintViolation<Patient>> iterator = validationErrors.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Patient> error = iterator.next();
                errorMessages.put(error.getPropertyPath().toString(), error.getMessage());
            }
        }
        return errorMessages;
    }
}
