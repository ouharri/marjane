package com.marjane.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //TODO Add sub-category-specific fields
}
