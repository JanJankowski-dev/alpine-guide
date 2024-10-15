package com.example.alpineguide.trips.api;

import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.TripDto;
import com.example.alpineguide.trips.dto.Trips;
import com.example.alpineguide.trips.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripsController {

    private final TripsService tripsService;

    @Autowired
    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @GetMapping
    public ResponseEntity<Trips> getTrips() {
        return ResponseEntity.ok(new Trips(tripsService.findAllTrips().stream().map(this::toDto).toList()));
    }

    @PostMapping
    public ResponseEntity<Integer> createTrip(@RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripsService.createTrip(tripDto).getId());
    }

    private TripDto toDto(Trip it) {
        return new TripDto(it.getStartDate(), it.getEndDate(), it.getDescription());
    }
}
