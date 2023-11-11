package com.marjane.controllers;

import com.marjane.models.Promotion;
import com.marjane.services.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller class to handle HTTP requests related to promotions.
 * Manages CRUD operations for promotions.
 *
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    /**
     * Creates a new promotion.
     *
     * @param promotion The promotion to be created.
     * @return ResponseEntity with the created promotion and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        Promotion savedPromotion = promotionService.savePromotion(promotion);
        return new ResponseEntity<>(savedPromotion, HttpStatus.CREATED);
    }

    /**
     * Retrieves all promotions.
     *
     * @return ResponseEntity with a list of promotions and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    /**
     * Retrieves a specific promotion by its ID.
     *
     * @param id The ID of the promotion to retrieve.
     * @return ResponseEntity with the retrieved promotion and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if the promotion is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable UUID id) {
        Optional<Promotion> promotion = promotionService.getPromotionById(id);
        return promotion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an existing promotion.
     *
     * @param id        The ID of the promotion to update.
     * @param promotion The updated promotion data.
     * @return ResponseEntity with the updated promotion and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if the promotion is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable UUID id, @RequestBody Promotion promotion) {
        if (promotionService.getPromotionById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        promotion.setId(id);
        Promotion updatedPromotion = promotionService.updatePromotion(promotion);
        return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
    }

    /**
     * Deletes a promotion by its ID.
     *
     * @param id The ID of the promotion to delete.
     * @return ResponseEntity with HTTP status 204 (No Content),
     * or HTTP status 404 (Not Found) if the promotion is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable UUID id) {
        if (promotionService.getPromotionById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        promotionService.deletePromotion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}