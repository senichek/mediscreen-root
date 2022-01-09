package com.mediscreen.patient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.controllers.PatientRestController;
import com.mediscreen.patient.models.Patient;
import com.mediscreen.patient.services.PatientService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = PatientRestController.class)
public class PatientRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createTest() throws Exception {
        Patient patient = new Patient();
        mockMvc.perform(post("/rest/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/rest/patient/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getPatientByIdTest() throws Exception {
        mockMvc.perform(get("/rest/patient/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateTest() throws JsonProcessingException, Exception {
        Patient patient = new Patient();
        mockMvc.perform(post("/rest/patient/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/rest/patient/delete/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
