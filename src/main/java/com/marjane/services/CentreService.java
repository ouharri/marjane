package com.marjane.services;

import com.marjane.Repositories.CentreRepository;
import com.marjane.models.Centre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing Centre entities.<br>
 *
 * This class provides basic CRUD operations for Centre entities.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CentreService {
    private final CentreRepository repository;

    /**
     * Save a new Centre.
     *
     * @param centre The Centre to be saved.
     * @return The saved Centre entity.
     */
    public Centre saveCentre(Centre centre) {
        return repository.save(centre);
    }

    /**
     * Retrieve all Centre entities.
     *
     * @return A list of all Centre entities.
     */
    public List<Centre> getAllCentres() {
        return repository.findAll();
    }

    /**
     * Retrieve a Centre by its ID.
     *
     * @param id The ID of the Centre to be retrieved.
     * @return Optional containing the retrieved Centre, or empty if not found.
     */
    public Optional<Centre> getCentreById(UUID id) {
        return repository.findById(id);
    }

    /**
     * Update an existing Centre.
     *
     * @param centre The Centre with updated information.
     * @return The updated Centre entity.
     */
    public Centre updateCentre(Centre centre) {
        return repository.save(centre);
    }

    /**
     * Delete a Centre by its ID.
     *
     * @param id The ID of the Centre to be deleted.
     */
    public void deleteCentre(UUID id) {
        repository.deleteById(id);
    }
}