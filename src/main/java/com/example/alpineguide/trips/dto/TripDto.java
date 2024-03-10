package com.example.alpineguide.trips.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.alpineguide.trips.dao.Trip}
 */
public record TripDto(Long id, LocalDate startDate, LocalDate endDate, String description) implements Serializable {
}
