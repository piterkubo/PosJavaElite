package br.com.kubo.events.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.kubo.events.model.Conference;
import br.com.kubo.events.model.Session;
import br.com.kubo.events.service.IConferenceService;

@RestController
public class ConferenceController {

    private final IConferenceService service;

    public ConferenceController(IConferenceService service) {
        this.service = service;
    }

    @PostMapping("/conferences")
    public ResponseEntity<Conference> addSession(@RequestBody Conference conference){
        return ResponseEntity.status(201).body(service.addConference(conference));
    }

    @GetMapping("/conferences")
    public ResponseEntity<List<Conference>> getAllConferences(){
        return ResponseEntity.ok(service.getAllConferences());
    }

    @GetMapping("/conferences/{id}")
    public ResponseEntity<Conference> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getConferenceByid(id));
    }


}