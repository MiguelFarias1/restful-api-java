package com.miguel.project.specialist.model.DTO;

import com.miguel.project.specialist.model.Person;

public record PersonDTO(String firstName, String lastName, String address, String email) {

    public PersonDTO(Person person) {
        this(person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail());
    }
}
