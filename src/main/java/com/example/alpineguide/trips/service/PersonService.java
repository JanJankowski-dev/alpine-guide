package com.example.alpineguide.trips.service;

import com.example.alpineguide.trips.dao.Person;
import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.PersonDto;
import com.example.alpineguide.trips.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final TripsService tripsService;

    public Person create(PersonDto personDTO) {
        Person person = new Person(personDTO.name());
        return personRepository.save(person);
    }

    public Optional<Person> find(Integer personId) {
        return personRepository.findById(personId);
    }

    public boolean assignTrip(Integer personId, Integer tripId) {
        Optional<Trip> optionalTrip = tripsService.find(tripId);
        Optional<Person> person = find(personId);
        if (optionalTrip.isEmpty()) {
            return false;
        }
        if (person.isEmpty()) {
            return false;
        }
        person.get().getTrips().add(optionalTrip.get());
        optionalTrip.get().getParticipants().add(person.get());
        return true;
    }
}
