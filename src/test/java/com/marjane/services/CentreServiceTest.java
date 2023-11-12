package com.marjane.services;

import com.marjane.Repositories.CentreRepository;
import com.marjane.models.Centre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CentreServiceTest {

    @Mock
    private CentreRepository repository;

    @InjectMocks
    private CentreService centreService;

    @Test
    void testSaveCentre() {
        // Arrange
        Centre centreToSave = new Centre(); // create your Centre object
        when(repository.save(centreToSave)).thenReturn(centreToSave);

        // Act
        Centre savedCentre = centreService.saveCentre(centreToSave);

        // Assert
        assertEquals(centreToSave, savedCentre);
        verify(repository, times(1)).save(centreToSave);
    }

    @Test
    void testGetAllCentres() {
        // Arrange
        List<Centre> centres = new ArrayList<>(); // create a list of Centres
        when(repository.findAll()).thenReturn(centres);

        // Act
        List<Centre> result = centreService.getAllCentres();

        // Assert
        assertEquals(centres, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetCentreById() {
        // Arrange
        UUID centreId = UUID.randomUUID();
        Centre centre = new Centre(); // create a Centre object
        when(repository.findById(centreId)).thenReturn(Optional.of(centre));

        // Act
        Optional<Centre> result = centreService.getCentreById(centreId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(centre, result.get());
        verify(repository, times(1)).findById(centreId);
    }

    @Test
    void testUpdateCentre() {
        // Arrange
        Centre centreToUpdate = new Centre(); // create your Centre object
        when(repository.save(centreToUpdate)).thenReturn(centreToUpdate);

        // Act
        Centre updatedCentre = centreService.updateCentre(centreToUpdate);

        // Assert
        assertEquals(centreToUpdate, updatedCentre);
        verify(repository, times(1)).save(centreToUpdate);
    }

    @Test
    void testDeleteCentre() {
        // Arrange
        UUID centreId = UUID.randomUUID();

        // Act
        centreService.deleteCentre(centreId);

        // Assert
        verify(repository, times(1)).deleteById(centreId);
    }
}