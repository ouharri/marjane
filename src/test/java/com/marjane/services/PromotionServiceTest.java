package com.marjane.services;

import com.marjane.Repositories.PromotionRepository;
import com.marjane.models.Promotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PromotionServiceTest {

    @Mock
    private PromotionRepository repository;

    @InjectMocks
    private PromotionService service;

    @Test
    void testSavePromotion() {
        // Given
        Promotion promotionToSave = new Promotion();
        when(repository.save(promotionToSave)).thenReturn(promotionToSave);

        // When
        Promotion savedPromotion = service.savePromotion(promotionToSave);

        // Then
        assertEquals(promotionToSave, savedPromotion);
        verify(repository, times(1)).save(promotionToSave);
    }

    @Test
    void testGetAllPromotions() {
        // Given
        List<Promotion> promotions = Arrays.asList(new Promotion(), new Promotion());
        when(repository.findAll()).thenReturn(promotions);

        // When
        List<Promotion> result = service.getAllPromotions();

        // Then
        assertEquals(promotions.size(), result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetPromotionById() {
        // Given
        UUID promotionId = UUID.randomUUID();
        Promotion promotion = new Promotion();
        when(repository.findById(promotionId)).thenReturn(Optional.of(promotion));

        // When
        Optional<Promotion> result = service.getPromotionById(promotionId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(promotion, result.get());
        verify(repository, times(1)).findById(promotionId);
    }

    @Test
    void testUpdatePromotion() {
        // Given
        Promotion existingPromotion = new Promotion();
        when(repository.save(existingPromotion)).thenReturn(existingPromotion);

        // When
        Promotion updatedPromotion = service.updatePromotion(existingPromotion);

        // Then
        assertEquals(existingPromotion, updatedPromotion);
        verify(repository, times(1)).save(existingPromotion);
    }

    @Test
    void testDeletePromotion() {
        // Given
        UUID promotionId = UUID.randomUUID();

        // When
        service.deletePromotion(promotionId);

        // Then
        verify(repository, times(1)).deleteById(promotionId);
    }
}
