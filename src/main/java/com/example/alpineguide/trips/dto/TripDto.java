package com.example.alpineguide.trips.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record TripDto(LocalDate startDate, LocalDate endDate, String description) implements Serializable {
}
