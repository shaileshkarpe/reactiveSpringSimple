package com.crossasyst.reactivespring.controller;

import com.crossasyst.reactivespring.dto.PersonRequest;
import com.crossasyst.reactivespring.entity.PersonEntity;
import com.crossasyst.reactivespring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class PersonController {
    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public Flux<PersonEntity> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("/person/{id}")
    public Mono<PersonRequest> getById(@PathVariable Long id) {

        return personService.getById(id);
    }

    @PostMapping("/person")
    public Mono<PersonEntity> addPerson(@RequestBody PersonEntity person) {
        return personService.addPerson(person);
    }

    @PutMapping("/persons/{id}")
    public Mono<PersonEntity> updatePerson(@PathVariable Long id, @RequestBody Mono<PersonEntity> person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/person/{id}")
    public Mono<Void> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }


}
