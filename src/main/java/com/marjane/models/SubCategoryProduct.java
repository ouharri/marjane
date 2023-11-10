package com.marjane.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //TODO Add other sub-category-specific fields

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryProduct category;

    @OneToMany(mappedBy = "subCategory")
    private List<Product> products;
}
