package com.miguel.project.specialist.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.miguel.project.specialist.controllers.PersonController;
import com.miguel.project.specialist.exceptions.EntityNotFoundException;
import com.miguel.project.specialist.model.Person;
import com.miguel.project.specialist.model.DTO.PersonDTO;
import com.miguel.project.specialist.repository.PersonRepository;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll() {
        
        var list = personRepository.findAll();

        var listDTO = list
                    .stream()
                    .map(PersonDTO::new).toList();

        listDTO.forEach(x -> x.add(linkTo(methodOn(PersonController.class).findById(x.getId())).withSelfRel()));

        logger.info("Find All persons in the database");

        return listDTO;
    }

    public PersonDTO findById(Long id) {

        var person = personRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhuma pessoa com o id: " + id));

        var personDTO = new PersonDTO(person);

        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        logger.info("Find person by id: " + id);

        return personDTO;
    }

    public PersonDTO save(Person person) {

        var personDTO = personRepository.save(person);

        logger.info("Save person: " + personDTO.getLastName());

        return new PersonDTO(personDTO);
    }

    private void changeData(Person old, Person newData) {

        old.setFirstName(newData.getFirstName());
        old.setLastName(newData.getLastName());
        old.setEmail(newData.getEmail());
        old.setPassword(newData.getPassword());
    }

    public PersonDTO update(Person person) {

        var personDatabase = personRepository.getReferenceById(person.getId());

        changeData(personDatabase, person);

        var update = personRepository.save(person);

        logger.info("Update person: " + update.getLastName());

        return new PersonDTO(update);
    }

    public void deleteById(Long id) {

        personRepository.deleteById(id);

        logger.info("Delete person by id: " + id);
    }
}
