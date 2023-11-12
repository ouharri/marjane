package com.marjane.services;

import com.marjane.Repositories.ProductRepository;
import com.marjane.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;


    @Test
    void testSaveProduct() {
        // Arrange
        Product productToSave = new Product(); // create your Product object
        when(repository.save(productToSave)).thenReturn(productToSave);

        // Act
        Product savedProduct = productService.saveProduct(productToSave);

        // Assert
        assertEquals(productToSave, savedProduct);
        verify(repository, times(1)).save(productToSave);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>(); // create a list of Products
        when(repository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(products, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = new Product(); // create a Product object
        when(repository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.getProductById(productId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
        verify(repository, times(1)).findById(productId);
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product productToUpdate = new Product(); // create your Product object
        when(repository.save(productToUpdate)).thenReturn(productToUpdate);

        // Act
        Product updatedProduct = productService.updateProduct(productToUpdate);

        // Assert
        assertEquals(productToUpdate, updatedProduct);
        verify(repository, times(1)).save(productToUpdate);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(repository, times(1)).deleteById(productId);
    }
}
