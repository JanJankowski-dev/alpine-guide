package com.example.alpineguide.trips.service;

import com.example.alpineguide.trips.dao.Trip;
import com.example.alpineguide.trips.dao.User;
import com.example.alpineguide.trips.dto.TripDto;
import com.example.alpineguide.trips.repository.TripsRepository;
import com.example.alpineguide.trips.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TripsService {
    private final TripsRepository tripsRepository;
    private final UserRepository userRepository;

    public TripsService(TripsRepository tripsRepository, UserRepository userRepository) {
        this.tripsRepository = tripsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Trip> getTrips() {
        System.out.println("\n\n\n\ngetTrips");
        return tripsRepository.findAll();
    }

    public void saveUSer(User user) {
        userRepository.save(user);
    }

    public void updateUsers(Trip trip, List<User> userList) {
        Optional<Trip> trip1 = tripsRepository.findById(Integer.parseInt(String.valueOf(trip.getId())));
        if (trip1.isPresent()) {
            trip1.get().setUserList(userList);
            tripsRepository.save(trip1.get());
        }

    }

    public void removeUser(Trip trip, User user) {
        Optional<Trip> trip1 = tripsRepository.findById(Integer.parseInt(String.valueOf(trip.getId())));
        trip1.ifPresent(value -> value.getUserList().remove(user));

    }


    public Trip create(TripDto tripDto) {
        Trip trip = new Trip();
        trip.setDescription(tripDto.description());
        trip.setStartDate(tripDto.startDate());
        trip.setEndDate(tripDto.endDate());
        return tripsRepository.save(trip);
    }
}
