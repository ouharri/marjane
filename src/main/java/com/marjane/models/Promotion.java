package com.marjane.models;

import com.marjane.Enums.PromotionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Date createdAt;

    private Date updatedAt;

    private Double percentage;

    private String description;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private PromotionStatus status;

    private Date dateDebut;

    private Date dateFin;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    private List<CategoryProduct> categoryProducts = new ArrayList<>();

    @ManyToMany
    private List<SubCategoryProduct> subCategoryProducts = new ArrayList<>();
}
