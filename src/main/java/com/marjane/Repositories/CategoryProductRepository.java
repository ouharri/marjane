package com.marjane.Repositories;

import com.marjane.models.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, UUID> {
}
