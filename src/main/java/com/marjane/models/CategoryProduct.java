package com.marjane.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Category name cannot be blank")
    @NotNull(message = "Category name must be provided")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<SubCategoryProduct> subCategories;
}
