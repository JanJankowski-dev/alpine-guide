package com.example.alpineguide.IT.trips.service;

import com.example.alpineguide.TestDbConfiguration;
import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.TripDto;
import com.example.alpineguide.trips.service.TripsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest(classes = {TestDbConfiguration.class})
@Sql(scripts = "classpath:trips.sql", executionPhase = BEFORE_TEST_CLASS)
public class TripsServiceTest {

    @Autowired
    TripsService tripsService;

    @Test
    public void shouldCreateTrips() {
        // given
        TripDto tripDto = new TripDto(
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(10),
                "Bike Trip");

        // when
        Trip trip = tripsService.createTrip(tripDto);

        // then
        Assertions.assertNotNull(trip);
        List<Trip> trips = tripsService.findAllTrips();
        Assertions.assertEquals(1, trips.size());
    }
}
