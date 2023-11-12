package com.marjane.services;

import com.marjane.Repositories.CategoryProductRepository;
import com.marjane.models.CategoryProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryProductServiceTest {

    @Mock
    private CategoryProductRepository repository;

    @InjectMocks
    private CategoryProductService categoryProductService;

    @Test
    void testSaveCategoryProduct() {
        // Arrange
        CategoryProduct categoryProductToSave = new CategoryProduct(); // create your CategoryProduct object
        when(repository.save(categoryProductToSave)).thenReturn(categoryProductToSave);

        // Act
        CategoryProduct savedCategoryProduct = categoryProductService.saveCategoryProduct(categoryProductToSave);

        // Assert
        assertEquals(categoryProductToSave, savedCategoryProduct);
        verify(repository, times(1)).save(categoryProductToSave);
    }

    @Test
    void testGetAllCategoryProducts() {
        // Arrange
        List<CategoryProduct> categoryProducts = new ArrayList<>(); // create a list of CategoryProducts
        when(repository.findAll()).thenReturn(categoryProducts);

        // Act
        List<CategoryProduct> result = categoryProductService.getAllCategoryProducts();

        // Assert
        assertEquals(categoryProducts, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetCategoryProductById() {
        // Arrange
        UUID categoryId = UUID.randomUUID();
        CategoryProduct categoryProduct = new CategoryProduct(); // create a CategoryProduct object
        when(repository.findById(categoryId)).thenReturn(Optional.of(categoryProduct));

        // Act
        Optional<CategoryProduct> result = categoryProductService.getCategoryProductById(categoryId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(categoryProduct, result.get());
        verify(repository, times(1)).findById(categoryId);
    }

    @Test
    void testUpdateCategoryProduct() {
        // Arrange
        CategoryProduct categoryProductToUpdate = new CategoryProduct(); // create your CategoryProduct object
        when(repository.save(categoryProductToUpdate)).thenReturn(categoryProductToUpdate);

        // Act
        CategoryProduct updatedCategoryProduct = categoryProductService.updateCategoryProduct(categoryProductToUpdate);

        // Assert
        assertEquals(categoryProductToUpdate, updatedCategoryProduct);
        verify(repository, times(1)).save(categoryProductToUpdate);
    }

    @Test
    void testDeleteCategoryProduct() {
        // Arrange
        UUID categoryId = UUID.randomUUID();

        // Act
        categoryProductService.deleteCategoryProduct(categoryId);

        // Assert
        verify(repository, times(1)).deleteById(categoryId);
    }
}
