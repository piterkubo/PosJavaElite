package br.com.kubo.events.controller;

import br.com.kubo.events.model.Session;
import br.com.kubo.events.model.Subscription;
import br.com.kubo.events.model.User;
import br.com.kubo.events.service.ISubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController  // informando que é uma rescontroller
public class SubcriptionController {


    // Injetando a dependencia
    private ISubscriptionService service;

    public SubcriptionController(ISubscriptionService service) {
        this.service = service;
    }



    @PostMapping("/subscriptions")
    public ResponseEntity<Subscription>addSubscription(@RequestBody Subscription subscription){
        return ResponseEntity.status(201).body(service.addSubscription(subscription));
    }

    @GetMapping("/subscriptions/user/{id}")
    public ResponseEntity<List<Subscription>>getById(@PathVariable Integer id){

        User user = new User();

        user.setUserId(id);

        return ResponseEntity.ok(service.getAllByUser(user));


    }


    @GetMapping("/subscriptions/session/{id}")
    public ResponseEntity<List<Subscription>> getBySession(@PathVariable Integer id){
        Session session = new Session();
        session.setIdSession(id);
        return ResponseEntity.ok(service.getAllBySession(session));
    }


}
