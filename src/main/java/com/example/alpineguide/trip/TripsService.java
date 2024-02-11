package com.example.alpineguide.trip;

import com.example.alpineguide.trip.dao.Trip;
import com.example.alpineguide.trip.repository.TripsRepository;
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
}
