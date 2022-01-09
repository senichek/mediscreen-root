package com.mediscreen.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.mediscreen.patient.models.Patient;
import com.mediscreen.patient.services.PatientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTest {
    
    @Autowired
    private PatientService patientService;

    @Test
    public void createUserTest() throws Exception {
        Patient expected = new Patient();
        expected.setFirstName("TestF");
        expected.setLastName("TestL");
        expected.setBirthdate(LocalDate.parse("1966-12-31"));
        expected.setSex("M");
        expected.setAddress("Test address");
        expected.setPhone("22-333-55");

        Patient actual = new Patient();
        actual = patientService.create(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void createUserWithExceptionTest() throws Exception {
        Patient expected = new Patient();
        expected.setFirstName("");
        expected.setLastName("TestL");
        expected.setBirthdate(LocalDate.parse("1966-12-31"));
        expected.setSex("M");
        expected.setAddress("Test address");
        expected.setPhone("22-333-55");

        Exception exception = assertThrows(Exception.class, () -> patientService.create(expected));
        assertTrue(exception.getMessage().contains("firstName=must not be empty"));
    }

    @Test
    public void updateUserTest() throws Exception {
        Patient expected = new Patient();
        expected.setFirstName("TestF");
        expected.setLastName("TestL");
        expected.setBirthdate(LocalDate.parse("1966-12-31"));
        expected.setSex("M");
        expected.setAddress("Test address");
        expected.setPhone("22-333-55");

        Patient actual = new Patient();
        actual = patientService.create(expected);
        actual.setFirstName("UpdatedF");
        actual.setLastName("UpdatedL");
        expected.setFirstName("UpdatedF");
        expected.setLastName("UpdatedL");
        assertEquals(expected, patientService.update(actual));
    }

    @Test
    public void updateUserWithExceptionTest() throws Exception {
        Patient expected = new Patient();
        expected.setFirstName("TestF");
        expected.setLastName("TestL");
        expected.setBirthdate(LocalDate.parse("1966-12-31"));
        expected.setSex("M");
        expected.setAddress("Test address");
        expected.setPhone("22-333-55");
        patientService.create(expected);

        expected.setFirstName("");
        Exception exception = assertThrows(Exception.class, () -> patientService.update(expected));
        assertTrue(exception.getMessage().contains("firstName=must not be empty"));

        expected.setId(112233);
        exception = assertThrows(Exception.class, () -> patientService.update(expected));
        assertTrue(exception.getMessage().contains("Entity with id 112233 does not exist."));
    }

    @Test
    public void getAllTest() {
        assertTrue(patientService.getAll().size() > 0);
    }

    @Test
    public void deleteTest() throws Exception {
        Patient expected = new Patient();
        expected.setFirstName("TestF");
        expected.setLastName("TestL");
        expected.setBirthdate(LocalDate.parse("1966-12-31"));
        expected.setSex("M");
        expected.setAddress("Test address");
        expected.setPhone("22-333-55");
        Patient toDelete = patientService.create(expected);
        patientService.delete(toDelete.getId());
        Exception exception = assertThrows(Exception.class, () -> patientService.delete(toDelete.getId()));
        assertTrue(exception.getMessage().contains("Entity with id " + toDelete.getId() +  " does not exist."));
    }

    @Test
    public void getByIdTest() throws Exception {
        Patient expected = new Patient();
        expected.setFirstName("TestF");
        expected.setLastName("TestL");
        expected.setBirthdate(LocalDate.parse("1966-12-31"));
        expected.setSex("M");
        expected.setAddress("Test address");
        expected.setPhone("22-333-55");

        Patient actual = new Patient();
        actual = patientService.create(expected);
        assertEquals(expected, patientService.getPatientById(actual.getId()));
    }

    @Test
    public void getByIdWithExceptionTest() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> patientService.getPatientById(125));
        assertTrue(exception.getMessage().contains("Entity with id 125 does not exist."));;
    }
}
