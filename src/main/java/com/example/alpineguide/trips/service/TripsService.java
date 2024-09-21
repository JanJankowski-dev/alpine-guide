package com.example.alpineguide.trips.service;

import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dto.TripDto;
import com.example.alpineguide.trips.repository.TripsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsService {
    private final TripsRepository tripsRepository;
    public TripsService(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }

    public List<Trip> getTrips() {
        return tripsRepository.findAll();
    }

    public Trip create(TripDto tripDto){
        Trip trip = new Trip();
        trip.setDescription(tripDto.description());
        trip.setStartDate(tripDto.startDate());
        trip.setEndDate(tripDto.endDate());
        return tripsRepository.save(trip);
    }
}
