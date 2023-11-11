package com.marjane.controllers;

import com.marjane.models.Centre;
import com.marjane.services.CentreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing Centre entities.<br>
 *
 * This class handles HTTP requests related to Centre entities.
 *
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/center")
public class CentreController {
    private final CentreService service;

    /**
     * Endpoint for creating a new Centre.
     *
     * @param centre The Centre to be created.
     * @return ResponseEntity with the created Centre and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Centre> createCentre(@RequestBody Centre centre) {
        Centre savedCentre = service.saveCentre(centre);
        return new ResponseEntity<>(savedCentre, HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving all Centre entities.
     *
     * @return ResponseEntity with a list of all Centre entities and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Centre>> getAllCentres() {
        List<Centre> centres = service.getAllCentres();
        return new ResponseEntity<>(centres, HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a Centre by its ID.
     *
     * @param id The ID of the Centre to be retrieved.
     * @return ResponseEntity with the retrieved Centre and HTTP status 200 (OK), or
     *         HTTP status 404 (Not Found) if the Centre is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Centre> getCentreById(@PathVariable UUID id) {
        return service.getCentreById(id)
                .map(centre -> new ResponseEntity<>(centre, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint for updating an existing Centre.
     *
     * @param id     The ID of the Centre to be updated.
     * @param centre The Centre with updated information.
     * @return ResponseEntity with the updated Centre and HTTP status 200 (OK), or
     *         HTTP status 404 (Not Found) if the Centre is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Centre> updateCentre(@PathVariable UUID id, @RequestBody Centre centre) {
        if (service.getCentreById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        centre.setId(id);
        Centre updatedCentre = service.updateCentre(centre);
        return new ResponseEntity<>(updatedCentre, HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a Centre by its ID.
     *
     * @param id The ID of the Centre to be deleted.
     * @return ResponseEntity with HTTP status 204 (No Content) if the Centre is
     *         successfully deleted, or HTTP status 404 (Not Found) if the Centre is
     *         not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable UUID id) {
        if (service.getCentreById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.deleteCentre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}