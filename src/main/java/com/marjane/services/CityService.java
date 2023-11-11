package com.marjane.services;

import com.marjane.Repositories.CityRepository;
import com.marjane.models.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing operations related to cities.
 * Handles communication between the controller and the repository for City entities.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    /**
     * Save a new city.
     *
     * @param city The city to be saved.
     * @return The saved city.
     */
    public City saveCity(City city) {
        log.info("Saving city: {}", city);
        return repository.save(city);
    }

    /**
     * Retrieve all cities.
     *
     * @return List of all cities.
     */
    public List<City> getAllCities() {
        log.info("Fetching all cities");
        return repository.findAll();
    }

    /**
     * Retrieve a city by its ID.
     *
     * @param cityId The ID of the city to retrieve.
     * @return Optional containing the city, or empty if not found.
     */
    public Optional<City> getCityById(UUID cityId) {
        log.info("Fetching city by ID: {}", cityId);
        return repository.findById(cityId);
    }

    /**
     * Update an existing city.
     *
     * @param city The updated city information.
     * @return The updated city.
     */
    public City updateCity(City city) {
        log.info("Updating city: {}", city);
        return repository.save(city);
    }

    /**
     * Delete a city by its ID.
     *
     * @param cityId The ID of the city to delete.
     */
    public void deleteCity(UUID cityId) {
        log.info("Deleting city by ID: {}", cityId);
        repository.deleteById(cityId);
    }
}
