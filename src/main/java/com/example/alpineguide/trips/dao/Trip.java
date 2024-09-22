package com.example.alpineguide.trips.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderColumn(name = "name")
    List<User> userList;

}
