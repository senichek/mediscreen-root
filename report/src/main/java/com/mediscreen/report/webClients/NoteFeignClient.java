package com.mediscreen.report.webClients;

import java.util.List;

import com.mediscreen.report.models.Note;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "notes", url = "localhost:8081")
@FeignClient(name = "notes", url = "${note.feign.client.url}") // URL is set up in .properties because we will use different URLs depending on the Profile.
public interface NoteFeignClient {

    @GetMapping(value = "/rest/patHistory/{userId}")
    List<Note> getNotesByUserId(@PathVariable("userId") Integer id);
}
