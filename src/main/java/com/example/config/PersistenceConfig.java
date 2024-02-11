package com.example.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.alpineguide.trip.repository")
public class PersistenceConfig {
}
