package com.marjane.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Add other product-specific fields

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategoryProduct subCategory;
}