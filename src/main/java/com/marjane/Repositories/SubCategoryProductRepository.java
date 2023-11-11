package com.marjane.Repositories;

import com.marjane.models.SubCategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryProductRepository extends JpaRepository<SubCategoryProduct, UUID> {
}