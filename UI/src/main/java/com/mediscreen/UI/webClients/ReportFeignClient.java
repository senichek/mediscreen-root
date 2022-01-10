package com.mediscreen.UI.webClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "ui-report", url = "localhost:8083")
@FeignClient(name = "ui-report", url = "${report.feign.client.url}") // URL is set up in .properties because we will use different URLs depending on the Profile.
public interface ReportFeignClient {

    @GetMapping(value = "/rest/assess/{userId}")
    String getReport(@PathVariable("userId") Integer id);
}
