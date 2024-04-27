package com.miguel.project.specialist.services;

import com.miguel.project.specialist.exceptions.EntityNotFoundException;
import com.miguel.project.specialist.model.DTO.PersonDTO;
import com.miguel.project.specialist.model.Person;
import com.miguel.project.specialist.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {

        return personRepository.findAll();

    }

    public PersonDTO findById(Long id) {

        var person = personRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não existe nenhuma pessoa com o id: " + id));

        return new PersonDTO(person);
    }

    public Person save(Person person) {

        return personRepository.save(person);
    }

    private void changeData(Person old, Person newData) {

        old.setFirstName(newData.getFirstName());
        old.setLastName(newData.getLastName());
        old.setEmail(newData.getEmail());
        old.setPassword(newData.getPassword());

    }

    public Person update(Person person) {

        var personDatabase = personRepository.getReferenceById(person.getId());

        changeData(personDatabase, person);

        return personRepository.save(person);
    }

    public void deleteById(Long id) {

        personRepository.deleteById(id);
    }
}
