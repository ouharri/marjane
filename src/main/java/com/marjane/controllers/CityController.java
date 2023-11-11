package com.marjane.controllers;

import com.marjane.models.City;
import com.marjane.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for managing City entities.<br>
 * <p>
 * This class provides endpoints for CRUD operations on City entities.
 *
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/city")
public class CityController {
    private final CityService cityService;

    /**
     * Endpoint to create a new City.
     *
     * @param city The City to be created.
     * @return ResponseEntity with the created City and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City savedCity = cityService.saveCity(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all City entities.
     *
     * @return ResponseEntity with the list of City entities and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a City by its ID.
     *
     * @param id The ID of the City to be retrieved.
     * @return ResponseEntity with the retrieved City and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if the City is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable UUID id) {
        return cityService.getCityById(id)
                .map(city -> new ResponseEntity<>(city, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to update an existing City.
     *
     * @param id   The ID of the City to be updated.
     * @param city The City with updated information.
     * @return ResponseEntity with the updated City and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if the City is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable UUID id, @RequestBody City city) {
        if (cityService.getCityById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        city.setId(id);
        City updatedCity = cityService.updateCity(city);
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a City by its ID.
     *
     * @param id The ID of the City to be deleted.
     * @return ResponseEntity with HTTP status 204 (No Content),
     * or HTTP status 404 (Not Found) if the City is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable UUID id) {
        if (cityService.getCityById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}