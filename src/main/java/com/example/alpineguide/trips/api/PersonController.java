package com.example.alpineguide.trips.api;

import com.example.alpineguide.trips.dao.Person;
import com.example.alpineguide.trips.dto.PersonDto;
import com.example.alpineguide.trips.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getTrips(@PathVariable("personId") Integer personId) {
        Optional<Person> person = personService.find(personId);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Integer> createTrip(@RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.create(personDto).getId());
    }
}
