package com.example.alpineguide.trip.api;

import com.example.alpineguide.trip.TripsService;
import com.example.alpineguide.trip.dao.Trip;
import com.example.alpineguide.trip.dto.TripDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/trips")
public class TripsController {

    private final TripsService tripsService;

    @Autowired
    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getTrips() {
        return ResponseEntity.ok(tripsService.getTrips().stream().map(this::toDto).toList());
    }

    private TripDto toDto(Trip it) {
        return new TripDto(it.getId(), it.getStartDate(), it.getEndDate(), it.getDescription());
    }
}
