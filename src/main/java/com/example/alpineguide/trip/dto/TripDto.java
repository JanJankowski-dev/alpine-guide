package com.example.alpineguide.trip.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.alpineguide.trip.dao.Trip}
 */
public record TripDto(Long id, LocalDate startDate, LocalDate endDate, String description) implements Serializable {
}
