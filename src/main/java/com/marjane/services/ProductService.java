package com.marjane.services;

import com.marjane.models.Product;
import com.marjane.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing Product entities.<br>
 *
 * This class provides methods to perform CRUD operations on Product entities.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    /**
     * Save a new Product.
     *
     * @param product The Product to be saved.
     * @return The saved Product.
     */
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    /**
     * Retrieve all Product entities.
     *
     * @return A list of all Product entities.
     */
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * Retrieve a Product by its ID.
     *
     * @param id The ID of the Product to be retrieved.
     * @return An Optional containing the retrieved Product, or an empty Optional if not found.
     */
    public Optional<Product> getProductById(UUID id) {
        return repository.findById(id);
    }

    /**
     * Update an existing Product.
     *
     * @param product The Product with updated information.
     * @return The updated Product.
     */
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    /**
     * Delete a Product by its ID.
     *
     * @param id The ID of the Product to be deleted.
     */
    public void deleteProduct(UUID id) {
        repository.deleteById(id);
    }
}