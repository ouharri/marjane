package com.marjane.services;

import com.marjane.Repositories.SubCategoryProductRepository;
import com.marjane.models.SubCategoryProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing operations related to sub-categories of products.
 * Handles communication between the controller and the repository for SubCategoryProduct entities.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SubCategoryProductService {
    private final SubCategoryProductRepository repository;

    /**
     * Save a new subcategory product.
     *
     * @param subCategoryProduct The subcategory product to be saved.
     * @return The saved subcategory product.
     */
    public SubCategoryProduct saveSubCategoryProduct(SubCategoryProduct subCategoryProduct) {
        return repository.save(subCategoryProduct);
    }

    /**
     * Get all subcategory products.
     *
     * @return A list of all subcategory products.
     */
    public List<SubCategoryProduct> getAllSubCategoryProducts() {
        return repository.findAll();
    }

    /**
     * Get a subcategory product by its ID.
     *
     * @param id The ID of the subcategory product.
     * @return An Optional containing the subcategory product if found, otherwise empty.
     */
    public Optional<SubCategoryProduct> getSubCategoryProductById(UUID id) {
        return repository.findById(id);
    }

    /**
     * Update an existing subcategory product.
     *
     * @param subCategoryProduct The updated subcategory product.
     * @return The updated subcategory product.
     */
    public SubCategoryProduct updateSubCategoryProduct(SubCategoryProduct subCategoryProduct) {
        return repository.save(subCategoryProduct);
    }

    /**
     * Delete a subcategory product by its ID.
     *
     * @param id The ID of the subcategory product to be deleted.
     */
    public void deleteSubCategoryProduct(UUID id) {
        repository.deleteById(id);
    }
}