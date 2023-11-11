package com.marjane.services;

import com.marjane.Repositories.PromotionRepository;
import com.marjane.models.Promotion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing operations related to promotions.
 * Handles communication between the controller and the repository for Promotion entities.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository repository;

    /**
     * Save a new Promotion.
     *
     * @param promotion The Promotion to be saved.
     * @return The saved Promotion.
     */
    public Promotion savePromotion(Promotion promotion) {
        //TODO Add any additional business logic or validation before saving
        return repository.save(promotion);
    }

    /**
     * Retrieve all Promotions.
     *
     * @return A list of all Promotions.
     */
    public List<Promotion> getAllPromotions() {
        return repository.findAll();
    }

    /**
     * Retrieve a Promotion by its ID.
     *
     * @param id The ID of the Promotion to be retrieved.
     * @return An Optional containing the retrieved Promotion, or an empty Optional if not found.
     */
    public Optional<Promotion> getPromotionById(UUID id) {
        return repository.findById(id);
    }

    /**
     * Update an existing Promotion.
     *
     * @param promotion The Promotion with updated information.
     * @return The updated Promotion.
     */
    public Promotion updatePromotion(Promotion promotion) {
        //TODO Add any additional business logic or validation before updating
        return repository.save(promotion);
    }

    /**
     * Delete a Promotion by its ID.
     *
     * @param id The ID of the Promotion to be deleted.
     */
    public void deletePromotion(UUID id) {
        repository.deleteById(id);
    }
}