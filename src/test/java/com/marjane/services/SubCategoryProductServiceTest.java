package com.marjane.services;

import com.marjane.Repositories.SubCategoryProductRepository;
import com.marjane.models.SubCategoryProduct;
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
class SubCategoryProductServiceTest {

    @Mock
    private SubCategoryProductRepository repository;

    @InjectMocks
    private SubCategoryProductService subCategoryProductService;

    @Test
    void testSaveSubCategoryProduct() {
        // Arrange
        SubCategoryProduct subCategoryProductToSave = new SubCategoryProduct(); // create your SubCategoryProduct object
        when(repository.save(subCategoryProductToSave)).thenReturn(subCategoryProductToSave);

        // Act
        SubCategoryProduct savedSubCategoryProduct = subCategoryProductService.saveSubCategoryProduct(subCategoryProductToSave);

        // Assert
        assertEquals(subCategoryProductToSave, savedSubCategoryProduct);
        verify(repository, times(1)).save(subCategoryProductToSave);
    }

    @Test
    void testGetAllSubCategoryProducts() {
        // Arrange
        List<SubCategoryProduct> subCategoryProducts = new ArrayList<>(); // create a list of SubCategoryProducts
        when(repository.findAll()).thenReturn(subCategoryProducts);

        // Act
        List<SubCategoryProduct> result = subCategoryProductService.getAllSubCategoryProducts();

        // Assert
        assertEquals(subCategoryProducts, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetSubCategoryProductById() {
        // Arrange
        UUID subCategoryProductId = UUID.randomUUID();
        SubCategoryProduct subCategoryProduct = new SubCategoryProduct(); // create a SubCategoryProduct object
        when(repository.findById(subCategoryProductId)).thenReturn(Optional.of(subCategoryProduct));

        // Act
        Optional<SubCategoryProduct> result = subCategoryProductService.getSubCategoryProductById(subCategoryProductId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(subCategoryProduct, result.get());
        verify(repository, times(1)).findById(subCategoryProductId);
    }

    @Test
    void testUpdateSubCategoryProduct() {
        // Arrange
        SubCategoryProduct subCategoryProductToUpdate = new SubCategoryProduct(); // create your SubCategoryProduct object
        when(repository.save(subCategoryProductToUpdate)).thenReturn(subCategoryProductToUpdate);

        // Act
        SubCategoryProduct updatedSubCategoryProduct = subCategoryProductService.updateSubCategoryProduct(subCategoryProductToUpdate);

        // Assert
        assertEquals(subCategoryProductToUpdate, updatedSubCategoryProduct);
        verify(repository, times(1)).save(subCategoryProductToUpdate);
    }

    @Test
    void testDeleteSubCategoryProduct() {
        // Arrange
        UUID subCategoryProductId = UUID.randomUUID();

        // Act
        subCategoryProductService.deleteSubCategoryProduct(subCategoryProductId);

        // Assert
        verify(repository, times(1)).deleteById(subCategoryProductId);
    }
}
