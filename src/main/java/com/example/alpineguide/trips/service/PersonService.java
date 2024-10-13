package com.example.alpineguide.trips.service;

import com.example.alpineguide.trips.dao.Person;
import com.example.alpineguide.trips.dto.PersonDTO;
import com.example.alpineguide.trips.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(PersonDTO personDTO) {
        Person person = new Person(personDTO.name());
        return personRepository.save(person);
    }

    public Optional<Person> find(Integer personId) {
        return personRepository.findById(personId);
    }

}
