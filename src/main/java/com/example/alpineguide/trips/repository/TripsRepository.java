package com.example.alpineguide.trips.repository;

import com.example.alpineguide.trips.dao.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripsRepository extends JpaRepository<Trip, Integer> {
}
