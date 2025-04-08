package com.extia.easyshopper.application.dto.request;

import com.extia.easyshopper.domain.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        @NotBlank(message = "The product name must not be blank.")
        String name,
        @NotBlank(message = "The product description must not be blank.")
        String description,
        @NotNull(message = "The product price is required.")
        @Positive(message = "The product price must be positive.")
        BigDecimal price,
        @NotNull(message = "The stock quantity is required.")
        @Positive(message = "The stock quantity must be positive.")
        Integer stock,
        @NotNull(message = "The product category is required.")
        ProductCategory category,
        @NotBlank(message = "The image url must not be blank.")
        String imageUrl
) {
}
