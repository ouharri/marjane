package com.marjane.controllers;

import com.marjane.models.CategoryProduct;
import com.marjane.services.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for managing CategoryProduct entities.<br/>
 * <p>
 * This class provides endpoints for CRUD operations on CategoryProduct entities.
 *
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/category-product")
public class CategoryProductController {
    private final CategoryProductService service;

    /**
     * Endpoint to create a new CategoryProduct.
     *
     * @param categoryProduct The CategoryProduct to be created.
     * @return ResponseEntity with the created CategoryProduct and HTTP status 201 (Created).
     */
    @PostMapping("/")
    public ResponseEntity<CategoryProduct> createCategoryProduct(@RequestBody CategoryProduct categoryProduct) {
        CategoryProduct createdCategoryProduct = service.saveCategoryProduct(categoryProduct);
        return new ResponseEntity<>(createdCategoryProduct, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all CategoryProducts.
     *
     * @return ResponseEntity with the list of CategoryProducts and HTTP status 200 (OK).
     */
    @GetMapping("/")
    public ResponseEntity<List<CategoryProduct>> getAllCategoryProducts() {
        List<CategoryProduct> categoryProducts = service.getAllCategoryProducts();
        return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a CategoryProduct by its ID.
     *
     * @param id The ID of the CategoryProduct to be retrieved.
     * @return ResponseEntity with the retrieved CategoryProduct and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if the CategoryProduct is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryProduct> getCategoryProductById(@PathVariable("id") UUID id) {
        return service
                .getCategoryProductById(id)
                .map(categoryProduct -> new ResponseEntity<>(categoryProduct, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to update an existing CategoryProduct.
     *
     * @param categoryProduct The CategoryProduct with updated information.
     * @return ResponseEntity with the updated CategoryProduct and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if the CategoryProduct is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryProduct> updateCategoryProduct(@PathVariable UUID id, @RequestBody CategoryProduct categoryProduct) {
        if (service.getCategoryProductById(categoryProduct.getId()).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        categoryProduct.setId(id);
        CategoryProduct updatedCategoryProduct = service.updateCategoryProduct(categoryProduct);
        return new ResponseEntity<>(updatedCategoryProduct, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a CategoryProduct by its ID.
     *
     * @param id The ID of the CategoryProduct to be deleted.
     * @return ResponseEntity with HTTP status 204 (No Content),
     * or HTTP status 404 (Not Found) if the CategoryProduct is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryProduct(@PathVariable("id") UUID id) {
        if (service.getCategoryProductById(id).isPresent()) {
            service.deleteCategoryProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}