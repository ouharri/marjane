package com.marjane.services;

import com.marjane.Repositories.CityRepository;
import com.marjane.models.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService cityService;

    @Test
    void testSaveCity() {
        // Arrange
        City cityToSave = new City(); // create your City object
        when(repository.save(cityToSave)).thenReturn(cityToSave);

        // Act
        City savedCity = cityService.saveCity(cityToSave);

        // Assert
        assertEquals(cityToSave, savedCity);
        verify(repository, times(1)).save(cityToSave);
    }

    @Test
    void testGetAllCities() {
        // Arrange
        List<City> cities = new ArrayList<>(); // create a list of Cities
        when(repository.findAll()).thenReturn(cities);

        // Act
        List<City> result = cityService.getAllCities();

        // Assert
        assertEquals(cities, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetCityById() {
        // Arrange
        UUID cityId = UUID.randomUUID();
        City city = new City(); // create a City object
        when(repository.findById(cityId)).thenReturn(Optional.of(city));

        // Act
        Optional<City> result = cityService.getCityById(cityId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(city, result.get());
        verify(repository, times(1)).findById(cityId);
    }

    @Test
    void testUpdateCity() {
        // Arrange
        City cityToUpdate = new City(); // create your City object
        when(repository.save(cityToUpdate)).thenReturn(cityToUpdate);

        // Act
        City updatedCity = cityService.updateCity(cityToUpdate);

        // Assert
        assertEquals(cityToUpdate, updatedCity);
        verify(repository, times(1)).save(cityToUpdate);
    }

    @Test
    void testDeleteCity() {
        // Arrange
        UUID cityId = UUID.randomUUID();

        // Act
        cityService.deleteCity(cityId);

        // Assert
        verify(repository, times(1)).deleteById(cityId);
    }
}
