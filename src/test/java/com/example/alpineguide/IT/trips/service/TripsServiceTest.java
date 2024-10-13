package com.example.alpineguide.IT.trips.service;

import com.example.alpineguide.TestDbConfiguration;
import com.example.alpineguide.trips.dao.Person;
import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.PersonDTO;
import com.example.alpineguide.trips.dto.TripDto;
import com.example.alpineguide.trips.service.PersonService;
import com.example.alpineguide.trips.service.TripsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest(classes = {TestDbConfiguration.class})
//@Sql(scripts = "classpath:trips.sql", executionPhase = BEFORE_TEST_CLASS)
@Transactional
public class TripsServiceTest {

    @Autowired
    TripsService tripsService;

    @Autowired
    PersonService personService;

    @Test
    public void shouldCreateTripsAndAssignPersons() {
        // given
        TripDto tripDto = new TripDto(
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(10),
                "Bike Trip");

        PersonDTO personDTO = new PersonDTO("DUNCAN");

        // when
        Trip trip = tripsService.createTrip(tripDto);
        Person person = personService.createPerson(personDTO);
        var result = personService.assignTrip(person.getId(), trip.getId());

        // then
        Optional<Person> expectedPerson = personService.find(person.getId());
        Assertions.assertNotNull(trip);
        List<Trip> trips = tripsService.findAllTrips();
        Assertions.assertEquals(1, trips.size());
        Assertions.assertTrue(result);
        Assertions.assertTrue(expectedPerson.isPresent());
        Assertions.assertEquals(expectedPerson.get().getTrips(), Set.of(trip));
    }
}
