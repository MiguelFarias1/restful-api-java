package com.miguel.project.specialist.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miguel.project.specialist.model.Person;
import com.miguel.project.specialist.model.DTO.PersonDTO;
import com.miguel.project.specialist.services.PersonService;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "People", description = "People controller")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(name = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll() {

        var personsDTO = personService.findAll();

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

        return ResponseEntity.ok(update);
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
