package com.marjane.services;

import com.marjane.Repositories.CategoryProductRepository;
import com.marjane.models.CategoryProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing {@link CategoryProduct} entities.<br>
 *
 * This class provides basic CRUD operations for the CategoryProduct entity.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryProductService {
    private final CategoryProductRepository repository;

    /**
     * Saves a new CategoryProduct entity.
     *
     * @param categoryProduct The CategoryProduct entity to be saved.
     * @return The saved CategoryProduct entity.
     */
    public CategoryProduct saveCategoryProduct(CategoryProduct categoryProduct) {
        log.debug("Request to save CategoryProduct : {}", categoryProduct);
        return repository.save(categoryProduct);
    }

    /**
     * Retrieves all CategoryProduct entities.
     *
     * @return List of CategoryProduct entities.
     */
    public List<CategoryProduct> getAllCategoryProducts() {
        log.debug("Request to get all CategoryProducts");
        return repository.findAll();
    }

    /**
     * Retrieves a CategoryProduct entity by its ID.
     *
     * @param id The ID of the CategoryProduct entity to be retrieved.
     * @return Optional containing the retrieved CategoryProduct entity, or an empty Optional if not found.
     */
    public Optional<CategoryProduct> getCategoryProductById(UUID id) {
        log.debug("Request to get CategoryProduct : {}", id);
        return repository.findById(id);
    }

    /**
     * Updates an existing CategoryProduct entity.
     *
     * @param categoryProduct The CategoryProduct entity with updated information.
     * @return The updated CategoryProduct entity.
     */
    public CategoryProduct updateCategoryProduct(CategoryProduct categoryProduct) {
        log.debug("Request to update CategoryProduct : {}", categoryProduct);
        return repository.save(categoryProduct);
    }

    /**
     * Deletes a CategoryProduct entity by its ID.
     *
     * @param id The ID of the CategoryProduct entity to be deleted.
     */
    public void deleteCategoryProduct(UUID id) {
        log.debug("Request to delete CategoryProduct : {}", id);
        repository.deleteById(id);
    }
}
