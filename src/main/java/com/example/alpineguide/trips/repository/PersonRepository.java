package com.example.alpineguide.trips.repository;

import com.example.alpineguide.trips.dao.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
