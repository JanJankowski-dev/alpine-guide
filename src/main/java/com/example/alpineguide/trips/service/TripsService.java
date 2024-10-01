package com.example.alpineguide.trips.service;

import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.TripDto;
import com.example.alpineguide.trips.repository.TripsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TripsService {
    private final TripsRepository tripsRepository;

    @Transactional
    public List<Trip> findAllTrips() {
        return tripsRepository.findAll();
    }

    public Trip createTrip(TripDto tripDto) {
        Trip trip = new Trip();
        trip.setDescription(tripDto.description());
        trip.setStartDate(tripDto.startDate());
        trip.setEndDate(tripDto.endDate());
        return tripsRepository.save(trip);
    }
}
