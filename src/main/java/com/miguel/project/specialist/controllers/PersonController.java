package com.miguel.project.specialist.controllers;

import com.miguel.project.specialist.model.DTO.PersonDTO;
import com.miguel.project.specialist.model.Person;
import com.miguel.project.specialist.services.PersonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(name = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll() {

        var persons = personService.findAll();

        var personsDTO = persons.stream().map(PersonDTO::new).toList();

        return ResponseEntity.ok(personsDTO);
    }

    @GetMapping(value = "/{id}", name = "findById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        var person = personService.findById(id);

        return ResponseEntity.ok(person);
    }

    @PatchMapping(value = "/{id}", name = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody Person person) {

        var update = personService.update(person);

        var personDTO = new PersonDTO(update);

        return ResponseEntity.ok(personDTO);
    }

    @PostMapping(name = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> create(@RequestBody Person person) {

        var created = personService.save(person);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(created)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        personService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
