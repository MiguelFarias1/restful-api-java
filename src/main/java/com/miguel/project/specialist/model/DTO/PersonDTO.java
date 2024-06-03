package com.miguel.project.specialist.model.DTO;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.miguel.project.specialist.model.Person;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"firstName", "lastName"})
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;

    public PersonDTO(Person person) {
        this(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getEmail());
    }
}
