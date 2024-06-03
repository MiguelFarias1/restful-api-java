package com.miguel.project.specialist.repositories;

import com.miguel.project.specialist.model.Person;
import com.miguel.project.specialist.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Mock
    private PersonRepository personRepository;

    private Person person;

    private Long validId;

    private Long invalidId;

    @BeforeEach
    void setup() {

        validId = 1L;

        invalidId = 99L;

        person = new Person();

        person.setId(validId);
        person.setFirstName("John");
        person.setLastName("Smith");
        person.setEmail("john.smith@gmail.com");
    }

    @Test
    void testFindByIdWhenIdIsValid() {

        Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

        var result = personRepository.findById(1L);

        Assertions.assertTrue(result.isPresent());

        Assertions.assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindByReturnNoneWhenIdIsInvalid() {

        Mockito.when(personRepository.findById(invalidId)).thenReturn(Optional.empty());

        var result = personRepository.findById(invalidId);

        Assertions.assertFalse(result.isPresent());
    }
}
