package com.mediscreen.report.webClients;

import com.mediscreen.report.models.Patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients", url = "${patient.feign.client.url}") // URL is set up in .properties because we will use different URLs depending on the Profile.
public interface PatientFeignClient {

    @GetMapping(value = "/rest/patient/{id}")
    Patient getPatientById(@PathVariable("id") Integer id);
}
