package com.extia.easyshopper.domain.model;

import com.extia.easyshopper.domain.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String imageUrl;
}
