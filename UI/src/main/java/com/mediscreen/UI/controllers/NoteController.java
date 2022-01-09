package com.mediscreen.UI.controllers;

import com.mediscreen.UI.models.Note;
import com.mediscreen.UI.webClients.NoteFeignClient;
import com.mediscreen.UI.webClients.PatientFeignClient;
import com.mediscreen.UI.webClients.ReportFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    @Autowired
    private NoteFeignClient noteFeignClient;

    @Autowired
    private PatientFeignClient patientFeignClient;

    @Autowired
    private ReportFeignClient reportFeignClient;

    @GetMapping(value = "/patHistory/{patientId}")
    public String showHistory(@PathVariable("patientId") Integer patientId, Model model, @RequestParam(name = "generateReport", required = false) String generateReport) throws Exception {
        model.addAttribute("notes", noteFeignClient.getNotesByUserId(patientId));
        model.addAttribute("patientId", patientId);
        if (generateReport != null && generateReport.equals("true")) {
            model.addAttribute("report", reportFeignClient.getReport(patientId));
        }
        return "note/list";
    }

    @GetMapping(value = "/patHistory/update/{noteId}")
    public String showUpdateForm(@PathVariable("noteId") String noteId, Model model) throws Exception {
        model.addAttribute("note", noteFeignClient.getNoteById(noteId));
        return "note/edit";
    }

    @PostMapping(value = "/patHistory/update/{noteId}")
    public String editNote(@PathVariable("noteId") String noteId, @Validated Note note, 
    BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            if (result.getFieldError("date") != null) {
                model.addAttribute("date", result.getFieldError("date").getDefaultMessage());
            }
            if (result.getFieldError("observation") != null) {
                model.addAttribute("observation", result.getFieldError("observation").getDefaultMessage());
            }
            return "note/edit";
        } else {
            noteFeignClient.updateNote(note.getOwnerId(), note);
            return "redirect:/patHistory/" + note.getOwnerId();
        }
    }

    @GetMapping(value = "/patHistory/delete/{patientId}/{noteId}")
    public String deleteNote(@PathVariable("patientId") String patientId, @PathVariable("noteId") String noteId) throws Exception {
        noteFeignClient.deleteNote(noteId);
        return "redirect:/patHistory/" + patientId;
    }

    @GetMapping(value = "/patHistory/add/{patientId}")
    public String showCreateForm(@PathVariable("patientId") Integer patientId, Model model) {
        model.addAttribute("patient", patientFeignClient.getPatientById(patientId));
        return "note/add";
    }

    @PostMapping(value = "/patHistory/add/{patientId}")
    public String createNote(@PathVariable("patientId") Integer patientId, @Validated Note note, 
    BindingResult result, Model model) throws Exception {
        model.addAttribute("patient", patientFeignClient.getPatientById(patientId));
        note.setOwnerId(patientId);
        if (result.hasErrors()) {
            if (result.getFieldError("date") != null) {
                model.addAttribute("date", result.getFieldError("date").getDefaultMessage());
            }
            if (result.getFieldError("observation") != null) {
                model.addAttribute("observation", result.getFieldError("observation").getDefaultMessage());
            }
            return "note/add";
        } else {
            noteFeignClient.createNote(patientId, note);
            return "redirect:/patHistory/" + note.getOwnerId();
        }
    }
}
