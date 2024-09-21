package com.example.alpineguide.trips.service;

import com.example.alpineguide.TestDbConfiguration;
import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.TripDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@SpringBootTest(classes = {TestDbConfiguration.class})
@Sql(scripts = "classpath:trips.sql")
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
        Trip trip = tripsService.create(tripDto);

        // then
        Assertions.assertNotNull(trip);
    }
}
