package com.miguel.project.specialist.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.miguel.project.specialist.model.Person;
import com.miguel.project.specialist.services.PersonService;

@Configuration
public class ApplicationConfiguration implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Override
    public void run(String... args) throws Exception {

        var person1 = new Person("Miguel", "Farias", "Rua teste", "miguel@teste.com", "12345678");

        var person2 = new Person("Gabriel", "Farias", "Rua teste2", "gabriel@teste.com", "12345678");

        var person3 = new Person("Lucas", "Silva", "Rua teste3", "lucas@teste.com", "12345678");


        var list = List.of(person1,person2,person3);

        for(var person : list) personService.save(person);
    }
}
