package com.example.alpineguide.trip.repository;

import com.example.alpineguide.trip.dao.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripsRepository extends JpaRepository<Trip, Integer> {
}
