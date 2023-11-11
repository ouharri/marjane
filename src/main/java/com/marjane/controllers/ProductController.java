package com.marjane.controllers;

import com.marjane.models.Product;
import com.marjane.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing Product entities.<br>
 *
 * This class provides RESTFUL endpoints to perform CRUD operations on Product entities.
 *
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/product")
public class ProductController {
    private final ProductService productService;

    /**
     * Create a new Product.
     *
     * @param product The Product to be created.
     * @return The created Product.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * Retrieve all Product entities.
     *
     * @return A list of all Product entities.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Retrieve a Product by its ID.
     *
     * @param id The ID of the Product to be retrieved.
     * @return The retrieved Product or a 404 response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing Product.
     *
     * @param id      The ID of the Product to be updated.
     * @param product The Product with updated information.
     * @return The updated Product or a 404 response if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        if (productService.getProductById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Delete a Product by its ID.
     *
     * @param id The ID of the Product to be deleted.
     * @return A 204 response if the Product was successfully deleted, or a 404 response if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        if (productService.getProductById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}